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

    @GetMapping("/route/from={from}&to={to}")
    private @ResponseBody Object getRoute(@PathVariable("from") String from, @PathVariable("to") String to) {
        return routeService.getRoute(from, to);
    }

    @PostMapping("/route")
    private Long saveRoute(@RequestBody Route route) {
        routeService.saveOrupdate(route);
        return route.getId();
    }
}
