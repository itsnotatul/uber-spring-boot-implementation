package com.example.UberDemoProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Taxi implements Serializable { // implements Serializable for removing redis caching error.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // this annotation is to tell JPA that this parameter will be auto generated in database and GenerationType.IDENTITY tells it to use auto increment feature on every addition of entity
    private Long id;

    private Double latitude;   // Latitude of the taxi location
    private Double longitude;  // Longitude of the taxi location
    private Boolean available; // Availability status
    private String taxiType;  // Go, XL, Sedan

    public String getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(String taxiType) {
        this.taxiType = taxiType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
