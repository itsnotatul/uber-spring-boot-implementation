package com.example.UberDemoProject.controller;

import com.example.UberDemoProject.dto.PaymentRequest;
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
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        Long bookingId = paymentRequest.getBookingId();
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        //Check whether or not it is valid booking ID
        if (!bookingOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking with ID " + bookingId + " not found.");
        }

        Booking booking = bookingOptional.get();

        // Call third-party payment service
        boolean paymentSuccess = paymentService.processExternalPayment();

        // Update booking status based on payment result
        if (paymentSuccess) {
            booking.setBookingStatus(BookingStatus.SUCCESS);
        } else {
            booking.setBookingStatus(BookingStatus.FAILED);
        }

        bookingRepository.save(booking);

        return ResponseEntity.ok("Payment " + (paymentSuccess ? "successful" : "failed") +
                ". Booking ID: " + booking.getId() + ". Status: " + booking.getBookingStatus());
    }
}
