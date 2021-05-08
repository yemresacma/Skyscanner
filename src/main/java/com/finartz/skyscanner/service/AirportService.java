package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Airport;
import com.finartz.skyscanner.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<Airport>();
        airportRepository.findAll().forEach(airport -> airports.add(airport));
        return airports;
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id).get();
    }

    public Airport getAirportByName(String name) {
        return airportRepository.findByAirportName(name);
    }

    public void saveOrUpdate(Airport airport) {
        airportRepository.save(airport);
    }

    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }
}
