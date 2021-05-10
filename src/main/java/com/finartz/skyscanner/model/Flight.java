package com.finartz.skyscanner.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "Flight")
public class Flight extends BaseEntity {
    @Column(nullable = false)
    private int initialTicketPrice;

    @Column(nullable = false)
    private int totalSeat;

    @Column(columnDefinition = "integer default 25")
    private int soldTicketNumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private Date date = new Date();

    @ManyToOne(optional = false)
    @JoinColumn(name = "company", nullable = false)
    private Company company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "route", nullable = false)
    private Route route;

    @OneToMany(mappedBy = "flightInfo")
    private List<Ticket> tickets;

    public Flight() {
    }

    public Flight(int initialTicketPrice, int totalSeat, Date date, Company company, Route route) {
        this.initialTicketPrice = initialTicketPrice;
        this.totalSeat = totalSeat;
        this.soldTicketNumber = 0;
        this.date = date;
        this.company = company;
        this.route = route;
    }

    public Flight(Long id, int initialTicketPrice, int totalSeat, Date date, Company company, Route route) {
        this(initialTicketPrice, totalSeat, date, company, route);
        this.setId(id);
    }

    public int getInitialTicketPrice() {
        return initialTicketPrice;
    }

    public void setInitialTicketPrice(int initialTicketPrice) {
        this.initialTicketPrice = initialTicketPrice;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public int getSoldTicketNumber() {
        return soldTicketNumber;
    }

    public void setSoldTicketNumber(int soldTicketNumber) {
        this.soldTicketNumber = soldTicketNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Flight [id=" + super.getId() + ", route=" + route.toString()
                + ", soldTicketNumber=" + Integer.toString(soldTicketNumber) +
                ", Date:" + date.toString() + "]";
    }
}
