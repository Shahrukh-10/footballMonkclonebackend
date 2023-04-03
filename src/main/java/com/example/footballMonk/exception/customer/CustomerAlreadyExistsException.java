package com.example.footballMonk.exception.customer;

public class CustomerAlreadyExistsException extends Exception{
    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
