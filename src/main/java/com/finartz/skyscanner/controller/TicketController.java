package com.finartz.skyscanner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finartz.skyscanner.model.Ticket;
import com.finartz.skyscanner.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/ticket")
    private Long saveTicket(@RequestBody Object ticketRequest) throws IOException {
        return ticketService.buyTicket(ticketRequest);
    }
}
