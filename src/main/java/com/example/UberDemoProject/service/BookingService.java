package com.example.UberDemoProject.service;

import com.example.UberDemoProject.dto.TaxiBookingRequest;
import com.example.UberDemoProject.enums.BookingStatus;
import com.example.UberDemoProject.model.Booking;
import com.example.UberDemoProject.model.Taxi;
import com.example.UberDemoProject.model.User;
import com.example.UberDemoProject.repo.BookingRepository;
import com.example.UberDemoProject.repo.TaxiRepository;
import com.example.UberDemoProject.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Taxi> taxiOptional = taxiRepository.findById(request.taxiId());
        if (!taxiOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Taxi with ID " + request.taxiId()+ " not found.");
        }

        Taxi taxi = taxiOptional.get();
        // Check if the taxi is available
        if (!taxi.getAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Taxi with ID " + request.taxiId() + " is not available.");
        }

        // Fetch the user based on userId
        Optional<User> userOptional = userRepository.findById(request.userId());
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + request.userId() + " not found.");
        }
        User user = userOptional.get();

        // Calculate fare
        double fare = pricingService.calculatePrice(request.taxiType(), request.bookingTime(), request.distance());

        // Mark taxi as unavailable (as it is now being booked: IN PROGRESS)
        taxi.setAvailable(false);
        taxiRepository.save(taxi);

        // save this booking in the system:
        Booking booking = new Booking();
        booking.setTaxi(taxi);
        booking.setUser(user);
        booking.setBookingTime(request.bookingTime());
        booking.setTaxiType(request.taxiType());
        booking.setDistance(request.distance());
        booking.setBookingStatus(BookingStatus.PENDING); //SAVE it as pending until the payment API response

        // Save the booking in the database
        bookingRepository.save(booking);

        return ResponseEntity.ok("Taxi booked successfully. Booking ID: " + booking.getId() +
                ". Status: PENDING. Please proceed to payment.");
    }

    public List<Booking> getUserBookings(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Booking> bookingPage = bookingRepository.findByUserId(userId,pageable);

        return  bookingPage.getContent();
    }
}
