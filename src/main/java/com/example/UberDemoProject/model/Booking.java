package com.example.UberDemoProject.model;

import com.example.UberDemoProject.enums.TaxiType;
import com.example.UberDemoProject.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore         // will ignore this while serialising the result, preventing infinite recursion
    private User user; // Reference to the User

    @ManyToOne
    @JoinColumn(name = "taxi_id")
    private Taxi taxi; // Reference to the Taxi

    private TaxiType taxiType;
    private LocalDateTime bookingTime;
    private double distance;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus; // Track payment status (PENDING, SUCCESS, FAILED)

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", taxi=" + taxi.getId() +
                ", taxiType=" + taxiType +
                ", bookingTime=" + bookingTime +
                ", distance=" + distance +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
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
