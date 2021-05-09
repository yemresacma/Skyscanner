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

    @PostMapping("/ticket")
    private Ticket saveTicket(@RequestBody Object ticketRequest) throws Exception {
        return ticketService.buyTicket(ticketRequest);
    }

    @DeleteMapping("/ticket")
    private void deleteTicket(@RequestBody Object ticketDeleteRequest) {
        ticketService.returnTicket(ticketDeleteRequest);
    }
}
