package com.example.UberDemoProject.controller;

import com.example.UberDemoProject.model.Taxi;
import com.example.UberDemoProject.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaxiController {
    @Autowired
    private TaxiService taxiService;

    @GetMapping("/taxis/available")
    public List<Taxi> getAvailableTaxis(@RequestParam Double latitude, @RequestParam Double longitude) {
        return taxiService.getAvailableTaxisNearLocation(latitude, longitude);
    }
}
