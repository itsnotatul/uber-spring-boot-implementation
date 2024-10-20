package com.example.UberDemoProject.service;

import com.example.UberDemoProject.model.Taxi;
import com.example.UberDemoProject.repo.TaxiRepository;
import com.example.UberDemoProject.utils.LocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiService {

    @Autowired
    private TaxiRepository taxiRepository;

    private static final double FIXED_RADIUS_KM = 10.0; // fixed radius of 10 KM is taken but configurable

    @Cacheable(value = "taxis", key = "'taxis_near_' + #latitude + '_' + #longitude")
    public List<Taxi> getAvailableTaxisNearLocation(Double latitude, Double longitude){
        // Calculate the min and max latitude and longitude based on the fixed radius
        double[] boundaries = LocationUtil.calculateBoundary(latitude, longitude, FIXED_RADIUS_KM);
        Double minLat = boundaries[0];
        Double maxLat = boundaries[1];
        Double minLng = boundaries[2];
        Double maxLng = boundaries[3];

        // Fetch from the database
        System.out.println("Fetching taxis from the database");
        return taxiRepository.findAvailableTaxisInArea(minLat, maxLat, minLng, maxLng);
    }

}
