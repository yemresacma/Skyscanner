package com.finartz.skyscanner.model;

import javax.persistence.*;

import com.sun.istack.NotNull;

import java.util.List;

@Entity
@Table(name = "Route",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"departurePoint", "arrivalPoint"}))
public class Route extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "departurePoint", nullable = false)
    private Airport departurePoint;

    @ManyToOne(optional = false)
    @JoinColumn(name = "arrivalPoint", nullable = false)
    private Airport arrivalPoint;

    @OneToMany(mappedBy = "route")
    private List<Flight> flights;

    public Route() {
    }

    public Route(Airport from, Airport to) {
        this.departurePoint = from;
        this.arrivalPoint = to;
    }

    public Airport getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(Airport arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public Airport getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(Airport departurePoint) {
        this.departurePoint = departurePoint;
    }

    @Override
    public String toString() {
        return "Route [id=" + super.getId() + ", from=" + departurePoint.toString()
                + ", to=" + arrivalPoint.toString() + "]";
    }
}


