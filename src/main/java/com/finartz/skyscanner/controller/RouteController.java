package com.finartz.skyscanner.controller;

import com.finartz.skyscanner.model.Route;
import com.finartz.skyscanner.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RouteController {
    @Autowired
    RouteService routeService;

    @GetMapping("/route")
    private @ResponseBody List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    /**
     * this method returns the all flights that matched with
     * given destination and departure points
     * @param from
     * @param to
     * @return
     */
    @GetMapping("/route/from={from}&to={to}")
    private @ResponseBody Route getRoute(@PathVariable("from") String from, @PathVariable("to") String to) {
        return routeService.getRoute(from, to);
    }

    @PostMapping("/route")
    private Long saveRoute(@RequestBody Route route) {
        routeService.saveOrUpdate(route);
        return route.getId();
    }
}
