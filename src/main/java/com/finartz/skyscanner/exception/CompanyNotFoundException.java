package com.finartz.skyscanner.exception;

import javax.persistence.EntityNotFoundException;

public class CompanyNotFoundException extends EntityNotFoundException {
    public CompanyNotFoundException(String param) {
        super("Company not found with the given " + param);
    }
}
