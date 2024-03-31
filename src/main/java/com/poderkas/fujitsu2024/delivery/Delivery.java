package com.poderkas.fujitsu2024.delivery;


import jakarta.persistence.*;
import org.springframework.core.style.ToStringCreator;
import org.springframework.util.Assert;
import java.time.LocalDateTime;


@Entity
@Table(name="DELIVERY")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime time;
    private String city;
    private String transporation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTransporation() {
        return this.transporation;
    }

    public void setTransporation(String transporation) {
        this.transporation = transporation;
    }


}
