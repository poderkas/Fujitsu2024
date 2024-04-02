package com.poderkas.fujitsu2024.delivery;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.*;
import org.springframework.core.style.ToStringCreator;
import org.springframework.util.Assert;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name="DELIVERY")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long timestamp;
    private String city;
    private String transporation;
    private Double price;

    //private double priceByWeather;

    public Delivery(Long timestamp, String city, String transporation) {
        this.timestamp = timestamp;
        this.city = city;
        this.transporation = transporation;
    }

    public Delivery() {

    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", time=" + timestamp +
                ", city='" + city + '\'' +
                ", transporation='" + transporation + '\'' +
                '}';
    }
}
