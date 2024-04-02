package com.poderkas.fujitsu2024.delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(path="api/delivery")
class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryController(DeliveryService deliveryService,
                              DeliveryRepository deliveryRepository) {
        this.deliveryService = deliveryService;
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping
    public Double getDeliveryPrice(@RequestBody Delivery clientsideDelivery){
        Delivery completeDelivery = deliveryRepository.findByTimestampAndCityAndTransporation(clientsideDelivery.getTimestamp(), clientsideDelivery.getCity(), clientsideDelivery.getTransporation());
        return completeDelivery.getPrice();
    }

    @PostMapping
    public void addNewDelivery(@RequestBody Delivery delivery){

        deliveryService.addNewDelivery(delivery);
    }

    @DeleteMapping(path = "{deliveryId}")
    public void deleteDelivery(@PathVariable("deliveryId") Long deliveryId){
        deliveryService.deleteDelivery(deliveryId);
    }

    @PutMapping(path = "{deliveryId}")
    public void updateDelivery(@PathVariable("deliveryId") Long deliveryId,
                               @RequestParam(required = false) Long timestamp,
                               @RequestParam(required = false) String city,
                               @RequestParam(required = false) String transportation){
        deliveryService.updateDelivery(deliveryId, timestamp, city, transportation);
    }
}
