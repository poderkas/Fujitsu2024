package com.poderkas.fujitsu2024.weather;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="WEATHER")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String stationName;
    private String stationCodeWMO;
    private double airTemperature;
    private double windSpeed;
    private String weatherPhenomenon;
    private int timestamp;


}