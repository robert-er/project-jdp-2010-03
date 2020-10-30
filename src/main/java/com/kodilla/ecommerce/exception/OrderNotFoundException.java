package com.kodilla.ecommerce.exception;


public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() {
        super("Wrong order id");
    }
    public OrderNotFoundException(final String message) {
        super(message);
    }
}
