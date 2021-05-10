package com.finartz.skyscanner.exception;

public class WrongRouteEntityException extends Exception{

    public WrongRouteEntityException() {
        super("Departure and Arrival points cannot be same");
    }
}
