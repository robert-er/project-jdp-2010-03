package com.kodilla.ecommerce.exception;

public class ProductAlreadyExistException extends RuntimeException{

    public ProductAlreadyExistException(String message){
        super(message);
    }
}
