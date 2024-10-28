package com.example.UberDemoProject.dto;

public record PaymentResponse(Long bookingId, String paymentStatus, String updatedBookingStatus, String message){

}

