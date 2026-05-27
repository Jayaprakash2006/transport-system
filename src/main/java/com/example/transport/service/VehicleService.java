package com.example.transport.service;

import com.example.transport.dto.VehicleRequest;
import com.example.transport.model.Vehicle;
import com.example.transport.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public String addVehicle(VehicleRequest request) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleName(request.getVehicleName());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setBrand(request.getBrand());
        vehicle.setPricePerDay(request.getPricePerDay());

        vehicle.setAvailable(true);

        vehicleRepository.save(vehicle);

        return "Vehicle added successfully";
    }

    public String updateVehicle(
            Long id,
            VehicleRequest request
    ) {

        Vehicle vehicle = vehicleRepository
                .findById(id)
                .orElse(null);

        if (vehicle == null) {
            return "Vehicle not found";
        }

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleName(request.getVehicleName());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setBrand(request.getBrand());
        vehicle.setPricePerDay(request.getPricePerDay());

        vehicleRepository.save(vehicle);

        return "Vehicle updated successfully";
    }

    public String deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository
                .findById(id)
                .orElse(null);

        if (vehicle == null) {
            return "Vehicle not found";
        }

        vehicleRepository.delete(vehicle);

        return "Vehicle deleted successfully";
    }

    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();
    }
}