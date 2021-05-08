package com.finartz.skyscanner.controller;

import com.finartz.skyscanner.model.Airport;
import com.finartz.skyscanner.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AirportController {
    @Autowired
    AirportService airportService;

    /**
     * This method is used to retrive all airports
     * @return returns all airports in the database
     */
    @GetMapping("/airport")
    private List<Airport> getAllAirport()
    {
        return airportService.getAllAirports();
    }

    /**
     * This method is used to retrive airport object
     * that matches with the id in the url
     * @param id airport id
     * @return airport object
     */
    @GetMapping("/airport/id={id}")
    private Airport getAirport(@PathVariable("id") Long id)
    {
        return airportService.getAirportById(id);
    }

    /**
     * This method is used to retrive airport object
     * that matches with the name in the url
     * @param name airport name
     * @return airport object
     */
    @GetMapping("/airport/name={airportName}")
    private Airport getAirport(@PathVariable("airportName") String name)
    {
        return airportService.getAirportByName(name);
    }

    /**
     * This method is used to delete airport object
     * that matches with the id in the url
     * @param id airport id
     */
    @DeleteMapping("/airport/id={id}")
    private void deleteAirport(@PathVariable("id") Long id)
    {
        airportService.deleteById(id);
    }

    /**
     * This method is used to save airport object to
     * the database
     * @param airport airport object
     * @return airport id
     */
    @PostMapping("/airport")
    private Long saveAirport(@RequestBody Airport airport)
    {
        airportService.saveOrUpdate(airport);
        return airport.getId();
    }
}
