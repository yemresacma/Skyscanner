package com.finartz.skyscanner.service;

import com.finartz.skyscanner.exception.RouteNotFoundException;
import com.finartz.skyscanner.exception.WrongRouteEntityException;
import com.finartz.skyscanner.model.Route;
import com.finartz.skyscanner.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RouteNotFoundException());
    }

    public void saveOrUpdate(Route route) throws WrongRouteEntityException {
        if (route.getArrivalPoint().getId() != route.getDeparturePoint().getId()) {
            routeRepository.save(route);
        } else {
            throw new WrongRouteEntityException();
        }
    }

}
