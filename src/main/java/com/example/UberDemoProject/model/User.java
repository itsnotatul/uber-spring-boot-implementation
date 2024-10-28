package com.example.UberDemoProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "app_user") // user table is already reserved in postgres
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Assuming one user can have multiple bookings
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude 
    private Set<Booking> bookings;


}
