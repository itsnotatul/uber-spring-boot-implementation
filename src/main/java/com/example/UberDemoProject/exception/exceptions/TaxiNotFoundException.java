package com.example.UberDemoProject.exception.exceptions;

public class TaxiNotFoundException extends RuntimeException{
    public TaxiNotFoundException(String message){
        super(message);
    }
}
