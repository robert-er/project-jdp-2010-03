package com.kodilla.ecommerce.exception;

public class UserAlreadyBlocked extends Exception {
    public UserAlreadyBlocked(String message) {
        super(message);
    }
}
