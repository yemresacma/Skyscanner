package com.finartz.skyscanner.model;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket extends BaseEntity {
    @Column(updatable = false)
    String creditCardInfo;
    int ticketPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flightInfo", nullable = false)
    private Flight flightInfo;

    public Ticket() {
    }

    public Ticket(String creditCardInfo, Flight flightInfo) {
        this.creditCardInfo = creditCardInfo;
        this.flightInfo = flightInfo;
    }

    public Ticket(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Flight getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(Flight flightInfo) {
        this.flightInfo = flightInfo;
    }
}
