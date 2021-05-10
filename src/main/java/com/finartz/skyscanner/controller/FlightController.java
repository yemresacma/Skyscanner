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

    /**
     * this method returns the all flights that matched with given route
     * @param from
     * @param to
     * @return
     */
    @GetMapping("/flight/from={from}&to={to}")
    private @ResponseBody List<Flight> getFlight(@PathVariable("from") String from, @PathVariable("to") String to) {
        return flightService.getFlight(from, to);
    }

    @PostMapping("/flight")
    private Long saveFlight(@RequestBody Flight flight) throws Exception {
        flightService.save(flight);
        return flight.getId();
    }
}
