package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Flight;
import com.finartz.skyscanner.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
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
        try {
            return flightRepository.getFlight(from, to, companyName);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    public void saveOrUpdate(Flight flight) {
        LocalDate date = flight.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        date.plusDays(10);

        if (date.isAfter(LocalDate.now())) {
            flightRepository.save(flight);
        } else
            throw new DateTimeException("The flight must be scheduled at least 10 day before. ");

    }
}
