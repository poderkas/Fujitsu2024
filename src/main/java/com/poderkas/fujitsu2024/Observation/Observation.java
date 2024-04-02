package com.poderkas.fujitsu2024.Observation;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "observations")
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="OBSERVATIONS")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @XmlAttribute(name = "timestamp")
    private Long timestamp;

    @OneToMany(cascade = CascadeType.ALL)
    @XmlElement(name = "station")
    private List<Station> stationList;

    public Station getStationByName(String stationName){
        for (Station station : stationList) {
            if (station.getName().equals(stationName)){
                return station;
            }
        }
        return null;
    }
}
