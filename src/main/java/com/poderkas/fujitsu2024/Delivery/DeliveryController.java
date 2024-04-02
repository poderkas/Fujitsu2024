package com.poderkas.fujitsu2024.Delivery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
        Delivery completeDelivery = deliveryService.getDeliveryByClientSideParams(clientsideDelivery);
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

}
