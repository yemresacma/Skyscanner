package com.finartz.skyscanner.exception;

import javax.persistence.EntityNotFoundException;

public class RouteNotFoundException extends EntityNotFoundException {
    public RouteNotFoundException() {
        super("No route available for given points");
    }
}
