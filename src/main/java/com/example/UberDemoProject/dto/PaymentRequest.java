package com.example.UberDemoProject.dto;

// with help of record, now its immutable and no setters obviously as DTO's purpose is to pass the data around and to not get modified after creation.
public record PaymentRequest(Long bookingID){

};

