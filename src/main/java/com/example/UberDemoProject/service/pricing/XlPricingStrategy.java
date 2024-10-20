package com.example.UberDemoProject.service.pricing;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public class XlPricingStrategy implements PricingStrategy{

    private static final double BASE_PRICE = 200.0;

    @Override
    public double calculateBasePrice() {
        return BASE_PRICE;
    }

    @Override
    public double calculateTotalPrice(LocalDateTime bookingTime, double distance) {
        double price = calculateBasePrice();
        price*=distance;
        LocalTime bookingLocalTime = bookingTime.toLocalTime();
        if (bookingLocalTime.isAfter(LocalTime.MIDNIGHT) && bookingLocalTime.isBefore(LocalTime.of(6, 0))) {
            price += 20; // Surcharge for night booking
        }
        return price;
    }
}
