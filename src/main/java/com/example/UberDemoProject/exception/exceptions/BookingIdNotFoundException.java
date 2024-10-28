package com.example.UberDemoProject.exception.exceptions;

public class BookingIdNotFoundException extends  RuntimeException{
    public BookingIdNotFoundException(String message){
        super(message);
    }
}
