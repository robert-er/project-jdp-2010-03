package com.kodilla.ecommerce.exception;

public class UserAlreadyExists extends RuntimeException {

        public UserAlreadyExists(String message) {
            super(message);
        }
}
