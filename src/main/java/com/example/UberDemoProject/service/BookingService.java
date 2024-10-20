package com.example.UberDemoProject.service;

import com.example.UberDemoProject.TaxiBookingRequest;
import com.example.UberDemoProject.model.Taxi;
import com.example.UberDemoProject.repo.TaxiRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private final PricingService pricingService;
    private final TaxiRepository taxiRepository;


    public BookingService(PricingService pricingService, TaxiRepository taxiRepository) {
        this.pricingService = pricingService;
        this.taxiRepository = taxiRepository;
    }

    public ResponseEntity<String> bookTaxi(TaxiBookingRequest request) {

        // Check if the taxi ID exists
        Optional<Taxi> taxi = taxiRepository.findById(request.getTaxiId());
        if (!taxi.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Taxi with ID " + request.getTaxiId() + " not found.");
        }

        // Check if the taxi is available
        if (!taxi.get().getAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Taxi with ID " + request.getTaxiId() + " is not available.");
        }
        // Calculate fare
        double fare = pricingService.calculatePrice(request.getTaxiType(), request.getBookingTime(), request.getDistance());

        // Mark taxi as unavailable (as it is now booked)
        taxi.get().setAvailable(false);
        taxiRepository.save(taxi.get());

        return ResponseEntity.ok("Taxi booked successfully. Price: " + fare);
    }
}
