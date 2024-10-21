package com.example.UberDemoProject.repo;

import com.example.UberDemoProject.model.Taxi;
import com.example.UberDemoProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
}
