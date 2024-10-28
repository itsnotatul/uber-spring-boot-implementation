package com.example.UberDemoProject.controller;

import com.example.UberDemoProject.dto.TaxiBookingRequest;
import com.example.UberDemoProject.dto.TaxiBookingResponse;
import com.example.UberDemoProject.model.Booking;
import com.example.UberDemoProject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/taxi/book")  // post endpoint for booking taxi
    public ResponseEntity<TaxiBookingResponse> bookTaxi(@RequestBody TaxiBookingRequest request) {
        TaxiBookingResponse taxiBookingResponse =  bookingService.bookTaxi(request);
        return ResponseEntity.ok(taxiBookingResponse);

    }

    //endpoint to fetch paginated list of bookings by user
    @GetMapping("/user/booking")
    public ResponseEntity<List<Booking>> getUserBookings(@RequestParam Long userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        List<Booking> bookings = bookingService.getUserBookings(userId, page, size);

        return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
    }
}
