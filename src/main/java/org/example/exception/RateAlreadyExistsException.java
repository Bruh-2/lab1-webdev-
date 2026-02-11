package org.example.exception;

public class RateAlreadyExistsException extends RuntimeException {
    public RateAlreadyExistsException(String message) {
        super(message);
    }
}
