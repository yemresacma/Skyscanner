package com.finartz.skyscanner.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class AirportExistsException extends DataIntegrityViolationException {
    public AirportExistsException() {
        super("Airport not found with the given name");
    }
}
