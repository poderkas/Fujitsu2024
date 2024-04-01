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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;
    private String city;
    private String transporation;

    //private double priceByWeather;

    public Delivery(LocalDateTime time, String city, String transporation) {
        this.time = time;
        this.city = city;
        this.transporation = transporation;
    }

    public Delivery() {

    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", time=" + time +
                ", city='" + city + '\'' +
                ", transporation='" + transporation + '\'' +
                '}';
    }
}
