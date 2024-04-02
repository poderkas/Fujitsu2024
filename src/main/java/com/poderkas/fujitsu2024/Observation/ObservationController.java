package com.poderkas.fujitsu2024.Observation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/observations")
class ObservationController {

    private final ObservationService observationService;
    @Autowired
    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }

    @GetMapping
    public List<Observation> getObservation(){
        return observationService.getAllObservations();
    }

    /*
    @DeleteMapping(path = "{weatherId}")
    public void deleteWeather(@PathVariable("weatherId") Long weatherId){
        weatherService.deleteWeather(weatherId);
    }
    */
}
