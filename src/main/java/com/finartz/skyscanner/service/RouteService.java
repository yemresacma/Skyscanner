package com.finartz.skyscanner.service;

import com.finartz.skyscanner.model.Route;
import com.finartz.skyscanner.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {
    @Autowired
    RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<Route>();
        routeRepository.findAll().forEach(route -> routes.add(route));
        return routes;
    }

    public Route getRoute(String from, String to) {
        return routeRepository.getRoute(from, to)
                .orElseThrow(() -> new EntityNotFoundException("No route available for given points"));
    }

    public void saveOrUpdate(Route route) {
        if (route.getArrivalPoint().getId() != route.getDeparturePoint().getId()) {
            routeRepository.save(route);
        } else {
            throw new EntityExistsException("Departure and Arrival points cannot be same");
        }
    }

}
