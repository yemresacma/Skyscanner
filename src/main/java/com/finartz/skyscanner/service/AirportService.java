package com.finartz.skyscanner.service;

import com.finartz.skyscanner.exception.AirportExistsException;
import com.finartz.skyscanner.exception.AirportNotFoundException;
import com.finartz.skyscanner.model.Airport;
import com.finartz.skyscanner.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
            return airportRepository.findById(id)
                    .orElseThrow(() -> new AirportNotFoundException("id"));
    }

    public Airport getAirportByName(String name) {
        return airportRepository.findByName(name)
                .orElseThrow(() -> new AirportNotFoundException("name"));
    }

    public void saveOrUpdate(Airport airport) {
        try {
            airportRepository.save(airport);
        } catch (AirportExistsException e) {
            throw e;
        }
    }

    public void deleteById(Long id) {
        try {
            airportRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw e;
        }
    }
}
