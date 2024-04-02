package com.poderkas.fujitsu2024.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    public Delivery findByTimestampAndCityAndTransporation(Long timestamp, String city, String transportation);


}