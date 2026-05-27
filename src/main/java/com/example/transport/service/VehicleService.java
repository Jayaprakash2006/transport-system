package com.example.transport.service;

import com.example.transport.dto.VehicleRequest;
import com.example.transport.model.User;
import com.example.transport.model.Vehicle;
import com.example.transport.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public String addVehicle(
            VehicleRequest request,
            Authentication authentication
    ) {

        User owner =
                (User) authentication.getPrincipal();

        Vehicle vehicle = new Vehicle();

        vehicle.setOwner(owner);

        vehicle.setVehicleNumber(
                request.getVehicleNumber()
        );

        vehicle.setVehicleName(
                request.getVehicleName()
        );

        vehicle.setVehicleType(
                request.getVehicleType()
        );

        vehicle.setBrand(
                request.getBrand()
        );

        vehicle.setPricePerDay(
                request.getPricePerDay()
        );

        vehicleRepository.save(vehicle);

        return "Vehicle added successfully";
    }

    public String updateVehicle(
            Long id,
            VehicleRequest request,
            Authentication authentication
    ) {

        User currentUser =
                (User) authentication.getPrincipal();

        Vehicle vehicle = vehicleRepository
                .findById(id)
                .orElse(null);

        if (vehicle == null) {
            return "Vehicle not found";
        }

        if (!vehicle.getOwner()
                .getId()
                .equals(currentUser.getId())) {

            return "You can manage only your vehicles";
        }

        vehicle.setVehicleNumber(
                request.getVehicleNumber()
        );

        vehicle.setVehicleName(
                request.getVehicleName()
        );

        vehicle.setVehicleType(
                request.getVehicleType()
        );

        vehicle.setBrand(
                request.getBrand()
        );

        vehicle.setPricePerDay(
                request.getPricePerDay()
        );

        vehicleRepository.save(vehicle);

        return "Vehicle updated successfully";
    }

    public String deleteVehicle(
            Long id,
            Authentication authentication
    ) {

        User currentUser =
                (User) authentication.getPrincipal();

        Vehicle vehicle = vehicleRepository
                .findById(id)
                .orElse(null);

        if (vehicle == null) {
            return "Vehicle not found";
        }

        if (!vehicle.getOwner()
                .getId()
                .equals(currentUser.getId())) {

            return "You can manage only your vehicles";
        }

        vehicleRepository.delete(vehicle);

        return "Vehicle deleted successfully";
    }

    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();
    }

    public List<Vehicle> getMyVehicles(
            Authentication authentication
    ) {

        User owner =
                (User) authentication.getPrincipal();

        return vehicleRepository.findByOwner(owner);
    }
}