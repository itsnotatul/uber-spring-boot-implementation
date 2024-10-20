package com.example.UberDemoProject.service;

import com.example.UberDemoProject.enums.TaxiType;
import com.example.UberDemoProject.service.pricing.GoPricingStrategy;
import com.example.UberDemoProject.service.pricing.PricingStrategy;
import com.example.UberDemoProject.service.pricing.SedanPricingStrategy;
import com.example.UberDemoProject.service.pricing.XlPricingStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PricingService {

    public double calculatePrice(TaxiType taxiType, LocalDateTime bookingTime, double distance) {
        PricingStrategy pricingStrategy;

        // find the pricing Strategy for the respective taxi type
        switch (taxiType) {
            case go:
                pricingStrategy = new GoPricingStrategy();
                break;
            case sedan:
                pricingStrategy = new SedanPricingStrategy();
                break;
            case xl:
                pricingStrategy = new XlPricingStrategy();
                break;
            default:
                throw new IllegalArgumentException("Invalid taxi type");
        }

        return pricingStrategy.calculateTotalPrice(bookingTime, distance);
    }
}