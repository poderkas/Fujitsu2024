package com.poderkas.fujitsu2024.Delivery;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="DELIVERY")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long timestamp;
    private String city;
    private String transportation;
    private Double price;



    public Delivery(Long timestamp, String city, String transportation) {
        this.timestamp = timestamp;
        this.city = city;
        this.transportation = transportation;
    }


    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", time=" + timestamp +
                ", city='" + city + '\'' +
                ", transportation='" + transportation + '\'' +
                '}';
    }
}
