package com.poderkas.fujitsu2024.delivery;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }


    public List<Delivery> getDeliveries(){
        return deliveryRepository.findAll();
    }


    public void addNewDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public void deleteDelivery(Long deliveryId){
        if(deliveryRepository.existsById(deliveryId)){
            deliveryRepository.deleteById(deliveryId);
        }
        else{
            throw new IllegalStateException("Delivery with Id "+ deliveryId+ " does not exist.");
        }
    }
    @Transactional
    public void updateDelivery(Long deliveryId, LocalDateTime time, String city, String transportation){
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() ->
                new IllegalStateException("Delivery with Id "+ deliveryId+ " does not exist."));

        if(time != null && Objects.equals(delivery.getTime(), time)){
            delivery.setTime(time);
        }
    }
}
