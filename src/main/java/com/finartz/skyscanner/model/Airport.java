package com.finartz.skyscanner.model;

import javax.persistence.*;

@Entity
@Table(name = "Airport")
public class Airport extends BaseEntity {
    @Column(unique = true)
    private String airportName;

    public Airport() {
    }

    public Airport(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirporName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public String toString() {
        return "Airport [id=" + super.getId() + ", airportName=" + airportName + "]";
    }
}
