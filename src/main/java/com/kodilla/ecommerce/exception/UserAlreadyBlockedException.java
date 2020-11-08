package com.kodilla.ecommerce.exception;

public class UserAlreadyBlockedException extends RuntimeException {

    public UserAlreadyBlockedException(String message) {
        super(message);
    }
}
