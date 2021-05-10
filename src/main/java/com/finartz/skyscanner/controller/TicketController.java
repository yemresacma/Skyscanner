package com.finartz.skyscanner.controller;

import com.finartz.skyscanner.model.Ticket;
import com.finartz.skyscanner.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/ticket")
    private @ResponseBody List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/ticketNumber={id}")
    private @ResponseBody Ticket getTicket(@PathVariable("id") Long id) {
        return ticketService.getTicketById(id);
    }

    /**
     * This method is used to buy ticket
     * @param ticketRequest is json object with given structure below
     * "flightInfoId" -> flight id
     * "creditCardNumber" -> card number
     * @return saved ticket in case of success
     * @throws Exception in case of invalid card or flight informations
     */
    @PostMapping("/ticket")
    private Ticket saveTicket(@RequestBody Object ticketRequest) throws Exception {
        return ticketService.buyTicket(ticketRequest);
    }

    /**
     * This method is used to return a ticket
     * @param ticketDeleteRequest is is json object with given structe below
     * "ticketNumber" -> ticket number(id)
     */
    @DeleteMapping("/ticket")
    private void deleteTicket(@RequestBody Object ticketDeleteRequest) {
        ticketService.returnTicket(ticketDeleteRequest);
    }
}
