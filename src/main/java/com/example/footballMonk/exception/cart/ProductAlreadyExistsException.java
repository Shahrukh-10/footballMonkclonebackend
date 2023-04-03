package com.example.footballMonk.exception.cart;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
