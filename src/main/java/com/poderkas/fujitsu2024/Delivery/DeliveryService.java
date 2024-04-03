package com.poderkas.fujitsu2024.Delivery;

import com.poderkas.fujitsu2024.Exceptions.*;
import com.poderkas.fujitsu2024.Observation.Observation;
import com.poderkas.fujitsu2024.Observation.ObservationRepository;
import com.poderkas.fujitsu2024.Observation.Station;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.*;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ObservationRepository observationRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository,
                           ObservationRepository observationRepository) {
        this.deliveryRepository = deliveryRepository;
        this.observationRepository = observationRepository;
    }


    public List<Delivery> getDeliveries(){
        return deliveryRepository.findAll();
    }

    public Delivery getDeliveryByClientSideParams(Delivery incompleteDelivery){
        Delivery completeDelivery = deliveryRepository.findByTimestampAndCityAndTransportation(incompleteDelivery.getTimestamp(),incompleteDelivery.getCity(),incompleteDelivery.getTransportation());
        if(completeDelivery == null){
            throw new DeliveryNotFoundException("This delivery is not in the database");
        }
        return completeDelivery;
    }

    //Adds new delivery
    //Forbids duplication based on a combination of timestamp, city, and transportation method.
    public void addNewDelivery(Delivery delivery) throws WeatherException {
        Delivery alreadyExists =  deliveryRepository.findByTimestampAndCityAndTransportation(delivery.getTimestamp(),delivery.getCity(),delivery.getTransportation());
        if(alreadyExists!=null){
            throw new DuplicateDeliveryException("This delivery already exists and cannot be added.");
        }

        deliveryRepository.save(processAndReturnFinishedDeliveryObject(delivery));

    }

    public void deleteDelivery(Long deliveryId){
        if(deliveryRepository.existsById(deliveryId)){
            deliveryRepository.deleteById(deliveryId);
        }
        else{
            throw new IllegalStateException("Delivery with Id "+ deliveryId+ " does not exist.");
        }
    }


    //Delivery fee business logic
    //Receives delivery from client.
    //Only POST request fields needed.
    //
    public Delivery processAndReturnFinishedDeliveryObject(Delivery incompleteDelivery) throws WeatherException, DeliveryTimestampException, DeliveryCityException, DeliveryTransportationMethodException {
        Long timestamp = incompleteDelivery.getTimestamp();
        String city = incompleteDelivery.getCity();
        String transportationMethod = incompleteDelivery.getTransportation();
        String weatherStation = cityToStation(city);
        Observation obsevationAtGivenTime = observationRepository.findTopByTimestampLessThanEqualOrderByTimestampDesc(timestamp/1000);


        //Invalid city exception handling.
        if(!city.equals("Tallinn") && !city.equals("Tartu") && !city.equals("Pärnu")){
            throw new DeliveryCityException("This city is not included in the delivery service.");
        }

        //Invalid transportation method exception handling.
        if (!transportationMethod.equals("Car") && !transportationMethod.equals("Bike") && !transportationMethod.equals("Scooter")){
            throw new DeliveryTransportationMethodException("Delivery has a malformed or unsupported transportation method.");
        }


        //Invalid delivery timestamp exception handling
        if(obsevationAtGivenTime==null){
            throw new DeliveryTimestampException("Delivery timestamp is malformed or outside the range of server weather data.");
        }


        Station relevantStation = obsevationAtGivenTime.getStationByName(weatherStation);

        //Fields relevant for fee calculation
        Double airTemperature = relevantStation.getAirtemperature();
        Double windSpeed = relevantStation.getWindspeed();
        String weatherPhenomenon = relevantStation.getPhenomenon();

        Double deliveryFee = 0.0;

        //Calculation for Tallinn
        if(city.equals("Tallinn")){

            if (incompleteDelivery.getTransportation().equals("Car")){
                deliveryFee+=4.0;
            }

            if (incompleteDelivery.getTransportation().equals("Scooter")){
                deliveryFee+=3.5;
            }

            if (incompleteDelivery.getTransportation().equals("Bike")){
                deliveryFee+=3.0;
            }
        }

        //Calculation for Tartu
        if(city.equals("Tartu")){

            if (incompleteDelivery.getTransportation().equals("Car")){
                deliveryFee+=3.5;
            }

            if (incompleteDelivery.getTransportation().equals("Scooter")){
                deliveryFee+=3.0;
            }

            if (incompleteDelivery.getTransportation().equals("Bike")){
                deliveryFee+=2.5;
            }
        }

        //Calculation for Pärnu
        if(city.equals("Pärnu")){

            if (incompleteDelivery.getTransportation().equals("Car")){
                deliveryFee+=3.0;
            }

            if (incompleteDelivery.getTransportation().equals("Scooter")){
                deliveryFee+=2.5;
            }

            if (incompleteDelivery.getTransportation().equals("Bike")){
                deliveryFee+=2.0;
            }
        }

        if(isForbiddenWeather(relevantStation, incompleteDelivery.getTransportation())){
            throw new WeatherException("Usage of selected vehicle type is forbidden");
        }

        incompleteDelivery.setPrice(deliveryFee);
        return incompleteDelivery;
    }

    public Double weatherFees (Station station, String transportation){
        String[] snowRelatedPhenomenons = {"Light snow shower", "Moderate snow shower", "Heavy snow shower", "Light sleet", "Moderate sleet", "Light snowfall", "Moderate snowfall", "Heavy snowfall", "Blowing snow", "Drifting snow"};
        String[] rainRelatedPhenomenons = {"Light shower", "Moderate shower", "Heavy shower", "Light rain", "Moderate rain", "Heavy rain"};
        double extraFee = 0.0;

        //Airtemperature fees
        if(station.getAirtemperature()< -10.0 && transportation.equals("Scooter")||transportation.equals("Bike")){
            extraFee+=1.0;
        }
        if(station.getAirtemperature() > -10.0 && station.getAirtemperature() < 0.0 && transportation.equals("Scooter")||transportation.equals("Bike")){
            extraFee+=0.5;
        }
        //Windspeed fees
        if(station.getWindspeed() > 10.0 && station.getWindspeed() < 20.0 && transportation.equals("Bike")){
            extraFee+=0.5;
        }
        //Weather phenomenon fees
        //Snow
        if(Arrays.asList(snowRelatedPhenomenons).contains(station.getPhenomenon()) && transportation.equals("Scooter") || transportation.equals("Bike")){
            extraFee+=1.0;
        }
        //Rain
        if(Arrays.asList(rainRelatedPhenomenons).contains(station.getPhenomenon()) && transportation.equals("Scooter") || transportation.equals("Bike")){
            extraFee+=0.5;
        }
        return extraFee;
    }

    //Checks if vehicle is being used during a forbidden weather phenomenon.
    public Boolean isForbiddenWeather(Station station, String transportation){
        String[] forbiddenPhenomenons ={"Thunderstorm", "Thunder", "Hail", "Glaze"};
        if(transportation.equals("Bike") && station.getWindspeed() > 20.0){
          return true;
        }
        if((transportation.equals("Bike") || transportation.equals("Scooter")) && Arrays.asList(forbiddenPhenomenons).contains(station.getPhenomenon())){
            return true;
        }
        return false;
    }

    //Converts city names into weather station names.
    public String cityToStation(String city){
        if(city.equals("Tallinn")){
            return "Tallinn-Harku";
        }
        if(city.equals("Tartu")){
            return "Tartu-Tõravere";
        }
        if(city.equals("Pärnu")){
            return "Pärnu";
        }

        //This is for testing
        if (city.equals("Luna")){
            return "Kuu";
        }

        else{
            return "Selles linnas ei pakuta teenust.";
        }
    }
}
