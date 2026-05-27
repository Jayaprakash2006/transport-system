package com.example.transport.repository;

import com.example.transport.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {
}