package com.finartz.skyscanner.exception;

import javax.persistence.EntityNotFoundException;

public class AirportNotFoundException extends EntityNotFoundException {
    public AirportNotFoundException(String param) {
        super("Airport not found with the given " + param);
    }
}
