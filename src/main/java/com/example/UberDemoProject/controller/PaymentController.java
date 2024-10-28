package com.example.UberDemoProject.controller;

import com.example.UberDemoProject.dto.PaymentRequest;
import com.example.UberDemoProject.dto.PaymentResponse;
import com.example.UberDemoProject.enums.BookingStatus;
import com.example.UberDemoProject.model.Booking;
import com.example.UberDemoProject.repo.BookingRepository;
import com.example.UberDemoProject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        // Call third-party payment service
        PaymentResponse paymentResponse= paymentService.processExternalPayment(paymentRequest.bookingID());
        return ResponseEntity.ok(paymentResponse);
    }
}
