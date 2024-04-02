package com.poderkas.fujitsu2024.delivery;

import com.poderkas.fujitsu2024.Observation.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {

    public Delivery findByTimestampAndCityAndTransporation(Long timestamp, String city, String transportation);


}
