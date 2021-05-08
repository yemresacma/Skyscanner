package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Ticket;
import com.finartz.skyscanner.repository.FlightRepository;
import com.finartz.skyscanner.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    FlightRepository flightRepository;

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        ticketRepository.findAll().forEach(ticket -> tickets.add(ticket));
        return tickets;
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).get();
    }

    public void saveOrUpdate(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Long buyTicket(Object ticketRequestObject) {
        LinkedHashMap ticketRequest = ((LinkedHashMap) ticketRequestObject);
        Long flightInfoId = Long.parseLong(ticketRequest.get("flightInfoId").toString());
        String creditCardNumber = ((LinkedHashMap) ticketRequest).get("creditCardNumber").toString();

        // TODO parse and mask credit card and get payment

        return flightInfoId;
    }
}
