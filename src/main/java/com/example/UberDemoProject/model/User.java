package com.example.UberDemoProject.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "app_user") // user table is already somethinhg presenmt in postgre
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Assuming one user can have multiple bookings
    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bookings=" + bookings +
                '}';
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
