package com.finartz.skyscanner.controller;

import com.finartz.skyscanner.model.Flight;
import com.finartz.skyscanner.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/flight")
    private @ResponseBody List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flight/from={from}&to={to}&companyName={companyName}")
    private @ResponseBody Object getFlight(@PathVariable("from") String from, @PathVariable("to") String to,
                                           @PathVariable("companyName") String companyName) {
        return flightService.getFlight(from, to, companyName);
    }

    @PostMapping("/flight")
    private Long saveFlight(@RequestBody Flight flight) {
        flightService.saveOrUpdate(flight);
        return flight.getId();
    }
}
