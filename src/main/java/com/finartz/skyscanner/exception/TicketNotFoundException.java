package com.finartz.skyscanner.exception;

import javax.persistence.EntityNotFoundException;

public class TicketNotFoundException extends EntityNotFoundException {
    public TicketNotFoundException() {
        super("No ticket found for the given id");
    }
}
