package com.example.UberDemoProject.model;

import com.example.UberDemoProject.enums.TaxiType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taxi implements Serializable { // implements Serializable for removing redis caching error.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // this annotation is to tell JPA that this parameter will be auto generated in database and GenerationType.IDENTITY tells it to use auto increment feature on every addition of entity
    private Long id;

    private Double latitude;   // Latitude of the taxi location
    private Double longitude;  // Longitude of the taxi location
    private Boolean available; // Availability status

    @Enumerated(EnumType.STRING) // Maps the enum as a string in the database
    private TaxiType taxiType;  // Go, XL, Sedan
}
