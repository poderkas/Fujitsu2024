package com.poderkas.fujitsu2024.delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(path="api/delivery")
class DeliveryController {

    private final DeliveryService deliveryService;
    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<Delivery> getDeliveries(){
        return deliveryService.getDeliveries();
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
                               @RequestParam(required = false) LocalDateTime time,
                               @RequestParam(required = false) String city,
                               @RequestParam(required = false) String transportation){
        deliveryService.updateDelivery(deliveryId, time, city, transportation);
    }
}
