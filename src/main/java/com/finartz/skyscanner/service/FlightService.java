package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Flight;
import com.finartz.skyscanner.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<Flight>();
        flightRepository.findAll().forEach(flight -> flights.add(flight));
        return flights;
    }

    public Object getFlight(String from, String to, String companyName) {
        return flightRepository.getFlight(from, to, companyName);
    }

    public void saveOrUpdate(Flight flight) {
        flightRepository.save(flight);
    }
}
