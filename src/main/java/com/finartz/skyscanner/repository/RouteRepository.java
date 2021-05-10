package com.finartz.skyscanner.repository;

import com.finartz.skyscanner.model.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
    @Query("SELECT new com.finartz.skyscanner.model.Route(r.id, r.departurePoint, r.arrivalPoint) " +
            "FROM Route r " +
            "WHERE (r.departurePoint.name = :from AND r.arrivalPoint.name = :to) ")
    Optional<Route> getRoute(@Param("from") String from, @Param("to") String to);
}
