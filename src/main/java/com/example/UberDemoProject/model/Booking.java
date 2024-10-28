package com.example.UberDemoProject.model;

import com.example.UberDemoProject.enums.TaxiType;
import com.example.UberDemoProject.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore         // will ignore this while serialising the result, preventing infinite recursion
    @ToString.Exclude
    private User user; // Reference to the User

    @ManyToOne
    @JoinColumn(name = "taxi_id")
    @ToString.Exclude
    private Taxi taxi; // Reference to the Taxi

    private TaxiType taxiType;
    private LocalDateTime bookingTime;
    private double distance;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus; // Track payment status (PENDING, SUCCESS, FAILED)

    @ToString.Include(name = "userId")
    private Long getUserId() {
        return user != null ? user.getId() : null;
    }

    @ToString.Include(name = "taxiId")
    private Long getTaxiId() {
        return taxi != null ? taxi.getId() : null;
    }

}



