package com.poderkas.fujitsu2024.Observation;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@XmlRootElement(name = "station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Long wmocode;
    private Double longitude;
    private Double latitude;
    private String phenomenon;
    private Double visibility;
    private Double precipitations;
    private Double airpressure;
    private Double relativehumidity;
    private Double airtemperature;
    private Integer winddirection;
    private Double windspeed;
    private Double windspeedmax;
    private Integer waterlevel;
    private Integer waterlevel_eh2000;
    private Double watertemperature;
    private Double uvindex;
    private Double sunshineduration;
    private Integer globalradiation;

}