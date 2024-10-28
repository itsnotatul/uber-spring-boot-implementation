package com.example.UberDemoProject.service;

import com.example.UberDemoProject.dto.PaymentResponse;
import com.example.UberDemoProject.enums.BookingStatus;
import com.example.UberDemoProject.exception.exceptions.BookingIdNotFoundException;
import com.example.UberDemoProject.model.Booking;
import com.example.UberDemoProject.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PaymentService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private BookingRepository bookingRepository;
    private final String PAYMENT_API_URL = "https://sailok.free.beeceptor.com/payment";

    // Method to process an external payment
    public PaymentResponse processExternalPayment(Long bookingId) {

        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        //Check whether or not it is valid booking ID
        if (!bookingOptional.isPresent()) {
            throw new BookingIdNotFoundException("Booking with ID " + bookingId + " not found.");
        }

        Booking booking = bookingOptional.get();

        // Make HTTP call to the third-party payment API
        ResponseEntity<String> response = restTemplate.postForEntity(PAYMENT_API_URL, null, String.class);

        // Check if the response indicates payment success
         if(response.getStatusCode() == HttpStatus.OK){
             booking.setBookingStatus(BookingStatus.SUCCESS);
             bookingRepository.save(booking);
             return new PaymentResponse(bookingId, "Success", "SUCCESS", "Payment successful. Taxi is now booked");
         }

        booking.setBookingStatus(BookingStatus.FAILED);
        bookingRepository.save(booking);

        return new PaymentResponse(bookingId, "Failure", "FAILED", "Payment Failed. Booking Failed");

    }
}
