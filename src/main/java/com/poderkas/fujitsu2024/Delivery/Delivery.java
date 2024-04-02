package com.poderkas.fujitsu2024.Delivery;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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
