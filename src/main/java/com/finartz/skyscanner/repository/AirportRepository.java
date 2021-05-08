package com.finartz.skyscanner.repository;

import com.finartz.skyscanner.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {


    Airport findByAirportName(String airportName);
}
