package com.finartz.skyscanner.repository;

import com.finartz.skyscanner.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    @Query("SELECT f.company, f.initialTicketPrice, f.totalSeat, f.remainingSeat " +
            "FROM Flight f " +
            "WHERE (f.route.arrivalPoint.airportName = :from AND " +
            "f.route.arrivalPoint.airportName = :to AND " +
            "f.company.companyName = :companyName ) ")
    Object getFlight(@Param("from") String from, @Param("to") String to, @Param("companyName") String companyName);
}
