package com.finartz.skyscanner.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "Ticket")
public class Ticket extends BaseEntity {
    @Column(insertable = false, updatable = false)
    String creditCardInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flightInfo", nullable = false)
    private Flight flightInfo;

    public Ticket() {
    }

    public Ticket(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }

    public Flight getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }
}
