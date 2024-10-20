package com.example.UberDemoProject.model;

import com.example.UberDemoProject.enums.TaxiType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Taxi implements Serializable { // implements Serializable for removing redis caching error.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // this annotation is to tell JPA that this parameter will be auto generated in database and GenerationType.IDENTITY tells it to use auto increment feature on every addition of entity
    private Long id;

    private Double latitude;   // Latitude of the taxi location
    private Double longitude;  // Longitude of the taxi location
    private Boolean available; // Availability status

    @Enumerated(EnumType.STRING) // Maps the enum as a string in the database
    private TaxiType taxiType;  // Go, XL, Sedan


    public TaxiType getTaxiType() {
        return taxiType;
    }

    public void setTaxiType(TaxiType taxiType) {
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
