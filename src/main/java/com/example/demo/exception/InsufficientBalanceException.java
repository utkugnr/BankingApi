package com.example.demo.exception;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("Insufficient balance in the account.");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
