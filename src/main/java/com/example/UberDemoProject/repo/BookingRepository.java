package com.example.UberDemoProject.repo;

import com.example.UberDemoProject.model.Booking;
import com.example.UberDemoProject.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findById(Long id);
}
