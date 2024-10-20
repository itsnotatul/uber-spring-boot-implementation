package com.example.UberDemoProject.service.pricing;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface PricingStrategy {
    double calculateBasePrice();
    double calculateTotalPrice(LocalDateTime bookingTime, double distance);
}
