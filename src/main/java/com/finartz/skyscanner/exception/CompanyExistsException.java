package com.finartz.skyscanner.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class CompanyExistsException extends DataIntegrityViolationException {
    public CompanyExistsException() {
        super("Company not found with the given name");
    }
}
