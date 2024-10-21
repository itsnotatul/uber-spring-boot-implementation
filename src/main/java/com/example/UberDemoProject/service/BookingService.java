package com.example.UberDemoProject.service;

import com.example.UberDemoProject.TaxiBookingRequest;
import com.example.UberDemoProject.model.Booking;
import com.example.UberDemoProject.model.Taxi;
import com.example.UberDemoProject.model.User;
import com.example.UberDemoProject.repo.BookingRepository;
import com.example.UberDemoProject.repo.TaxiRepository;
import com.example.UberDemoProject.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private final PricingService pricingService;
    private final TaxiRepository taxiRepository;
    private final BookingRepository bookingRepository;  // Add the booking repository to save bookings
    private final UserRepository userRepository;


    public BookingService(PricingService pricingService, TaxiRepository taxiRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.pricingService = pricingService;
        this.taxiRepository = taxiRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> bookTaxi(TaxiBookingRequest request) {

        // Check if the taxi ID exists
        Optional<Taxi> taxiOptional = taxiRepository.findById(request.getTaxiId());
        Taxi taxi = taxiOptional.get();
        if (!taxiOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Taxi with ID " + request.getTaxiId()+ " not found.");
        }

        // Check if the taxi is available
        if (!taxi.getAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Taxi with ID " + request.getTaxiId() + " is not available.");
        }
        // Calculate fare
        double fare = pricingService.calculatePrice(request.getTaxiType(), request.getBookingTime(), request.getDistance());

        // Mark taxi as unavailable (as it is now booked)
        taxi.setAvailable(false);
        taxiRepository.save(taxi);

        // save this booking in the system:
        // Fetch the user based on userId
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + request.getUserId() + " not found.");
        }
        User user = userOptional.get();

        Booking booking = new Booking();
        booking.setTaxi(taxi);
        booking.setUser(user);
        booking.setBookingTime(request.getBookingTime());
        booking.setTaxiType(request.getTaxiType());
        booking.setDistance(request.getDistance());

        // Save the booking in the database
        bookingRepository.save(booking);

        return ResponseEntity.ok("Taxi booked successfully. Price: " + fare);
    }
}
