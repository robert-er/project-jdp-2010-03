package com.kodilla.ecommerce.exception;

public class UserIsNotBlockedException extends RuntimeException {

    public UserIsNotBlockedException(String message) {
        super(message);
    }
}
