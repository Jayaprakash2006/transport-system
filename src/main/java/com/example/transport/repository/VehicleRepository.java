package com.example.transport.repository;

import com.example.transport.model.User;
import com.example.transport.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByOwner(User owner);
}