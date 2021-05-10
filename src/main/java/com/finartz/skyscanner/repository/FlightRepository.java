package com.finartz.skyscanner.repository;

import com.finartz.skyscanner.model.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    @Query("SELECT new com.finartz.skyscanner.model.Flight(f.id, f.initialTicketPrice, f.totalSeat, f.date, f.company, f.route)" +
            "FROM Flight f " +
            "WHERE (f.route.departurePoint.name = :from AND " +
            "f.route.arrivalPoint.name = :to ) ")
    Optional<List<Flight>> getFlight(@Param("from") String from, @Param("to") String to);
}
