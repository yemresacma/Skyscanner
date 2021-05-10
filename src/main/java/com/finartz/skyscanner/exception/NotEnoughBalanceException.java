package com.finartz.skyscanner.exception;

public class NotEnoughBalanceException extends Exception{
    public NotEnoughBalanceException() {
        super("No enough balance in the account");
    }
}
