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

    public List<Flight> getFlight(String from, String to) {
         return flightRepository.getFlight(from, to)
                 .orElseThrow(() -> new EntityNotFoundException("No flight found for given route"));
    }

    public Flight getFlight(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No flight found for given id"));
    }

    /**
     * This method is used to save flight
     * @param flight
     * @throws Exception in case of wrong date data or
     * in case of wrong seat number or
     * in case of wrong ticket price
     */
    public void save(Flight flight) throws Exception {
        LocalDate date = (flight.getDate()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now().plusDays(10);

        if (date.isBefore(currentDate)) {
            throw new DateTimeException("The flight must be scheduled at least 10 day before.");
        } else if (flight.getTotalSeat() <= 0) {
            throw new Exception("Invalid number of seat argument");
        } else if (flight.getInitialTicketPrice() <= 0) {
            throw new Exception("Invalid ticket price argument");
        } else
            flightRepository.save(flight);
    }
}
