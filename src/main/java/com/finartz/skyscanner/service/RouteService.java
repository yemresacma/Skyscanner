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

    public Object getRoute(String from, String to) {
        try {
            return routeRepository.getRoute(from, to);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    public void saveOrupdate(Route route) {
        try {
            routeRepository.save(route);
        } catch (EntityExistsException e) {
            throw e;
        }
    }

}
