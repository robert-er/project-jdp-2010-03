package com.kodilla.ecommerce.exception;

public class UserIsNotBlocked extends RuntimeException {
    public UserIsNotBlocked(String message) {
        super(message);
    }
}
