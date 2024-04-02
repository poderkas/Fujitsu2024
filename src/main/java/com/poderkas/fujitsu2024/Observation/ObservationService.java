package com.poderkas.fujitsu2024.Observation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    @Autowired
    public ObservationService(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }


    public List<Observation> getAllObservations(){
        return observationRepository.findAll();
    }

    public void addNewObservation(Observation observation) {
        observationRepository.save(observation);
    }

    public void deleteObservation(Long observationId){
        if(observationRepository.existsById(observationId)){
            observationRepository.deleteById(observationId);
        }
        else{
            throw new IllegalStateException("Delivery with Id "+ observationId+ " does not exist.");
        }
    }

    @Scheduled(cron = "* */15 * * * *")
    void fetchObservation() throws MalformedURLException, JAXBException {
        URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");

        JAXBContext jaxbContext = JAXBContext.newInstance(Observation.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        Observation observation = (Observation) unmarshaller.unmarshal(url);
        System.out.println(observation.getStationList());
        observationRepository.save(observation);
    }

}