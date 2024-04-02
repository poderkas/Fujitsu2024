package com.poderkas.fujitsu2024;

import com.poderkas.fujitsu2024.Delivery.Delivery;
import com.poderkas.fujitsu2024.Delivery.DeliveryRepository;
import com.poderkas.fujitsu2024.Delivery.DeliveryService;
import com.poderkas.fujitsu2024.Exceptions.WeatherException;
import com.poderkas.fujitsu2024.Observation.Observation;
import com.poderkas.fujitsu2024.Observation.ObservationRepository;
import com.poderkas.fujitsu2024.Observation.Station;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class Fujitsu2024ApplicationTests {


    @Autowired
    private DeliveryRepository deliveryTesting;

    @Autowired
    private ObservationRepository observationTesting;

    @Autowired
    private DeliveryService testDeliveryService;

    @Test
    @Transactional
    void returnWeatherError(){

        //predefined station
        Station testStation = new Station();
        testStation.setName("Kuu");
        testStation.setWindspeed(30.0);
        testStation.setPhenomenon("Glaze");
        testStation.setAirtemperature(-200.0);

        List<Station> stations = new ArrayList<>();
        stations.add(testStation);

        //predefined testObservation
        Observation testObservation = new Observation();
        testObservation.setTimestamp(1000000000L);
        testObservation.setStationList(stations);

        Delivery testDelivery = new Delivery(1000000001000L,"Luna", "Bike");

        observationTesting.save(testObservation);
        Delivery testDelivery2 = new Delivery(1000000001000L,"Luna", "Scooter");

        assertThrows(WeatherException.class, ()->{testDeliveryService.processAndReturnFinishedDeliveryObject(testDelivery);});
        assertThrows(WeatherException.class, ()->{testDeliveryService.processAndReturnFinishedDeliveryObject(testDelivery2);});
    }

    @Test
    @Transactional
    void returnPriceCorrectly(){
        //TODO
        //predefined station
        Station testStation = new Station();
        testStation.setName("Kuu");
        testStation.setWindspeed(10.0);
        testStation.setPhenomenon("Rain");
        testStation.setAirtemperature(10.0);

        List<Station> stations = new ArrayList<>();
        stations.add(testStation);

        //predefined testObservation
        Observation testObservation = new Observation();
        testObservation.setTimestamp(1000000000L);
        testObservation.setStationList(stations);

        Delivery testDelivery = new Delivery(1000000001000L,"Luna", "Bike");

        observationTesting.save(testObservation);
        Delivery testDelivery2 = new Delivery(1000000001000L,"Luna", "Scooter");

    }
}
