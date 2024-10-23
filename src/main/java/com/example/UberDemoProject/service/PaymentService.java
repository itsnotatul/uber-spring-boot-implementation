package com.example.UberDemoProject.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String PAYMENT_API_URL = "https://sailok.free.beeceptor.com/payment";

    // Method to process an external payment
    public boolean processExternalPayment() {
        try {
            // Make HTTP call to the third-party payment API
            ResponseEntity<String> response = restTemplate.postForEntity(PAYMENT_API_URL, null, String.class);

            // Check if the response indicates payment success
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Payment failed
        }
    }
}
