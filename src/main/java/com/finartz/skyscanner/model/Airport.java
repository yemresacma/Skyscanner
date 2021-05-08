package com.finartz.skyscanner.model;

import javax.persistence.*;

@Entity
@Table(name = "Airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(unique = true)
    private String airportName;

    public Airport() {
    }

    public Airport(String airportName) {
        this.airportName = airportName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirporName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public String toString() {
        return "Airport [id=" + id + ", airportName=" + airportName + "]";
    }
}
