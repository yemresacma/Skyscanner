package com.finartz.skyscanner.exception;

import javax.persistence.EntityNotFoundException;

public class FlightNotFoundException extends EntityNotFoundException {
    public FlightNotFoundException(String param) {
        super("No flight found for given " + param);
    }
}
