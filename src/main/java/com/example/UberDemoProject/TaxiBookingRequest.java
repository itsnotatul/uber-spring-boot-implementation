package com.example.UberDemoProject;

import com.example.UberDemoProject.enums.TaxiType;

import java.time.LocalDateTime;

public class TaxiBookingRequest {

    private Long taxiId; // taxi unique identifier is an ID (Primary KEY in entity taxi)
    private TaxiType taxiType;
    private LocalDateTime bookingTime;
    private double distance;

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(TaxiType taxiType) {
        this.taxiType = taxiType;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
