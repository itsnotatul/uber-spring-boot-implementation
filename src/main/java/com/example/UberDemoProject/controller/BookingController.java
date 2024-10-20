package com.example.UberDemoProject.controller;

import com.example.UberDemoProject.TaxiBookingRequest;
import com.example.UberDemoProject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/taxis/book")
    public ResponseEntity<String> bookTaxi(@RequestBody TaxiBookingRequest request) {
        return bookingService.bookTaxi(request);

    }
}
