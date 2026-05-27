package com.example.transport.controller;

import com.example.transport.dto.VehicleRequest;
import com.example.transport.model.Vehicle;
import com.example.transport.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/add")
    public String addVehicle(
            @RequestBody VehicleRequest request,
            Authentication authentication
    ) {

        return vehicleService.addVehicle(
                request,
                authentication
        );
    }

    @PutMapping("/update/{id}")
    public String updateVehicle(
            @PathVariable Long id,
            @RequestBody VehicleRequest request,
            Authentication authentication
    ) {

        return vehicleService.updateVehicle(
                id,
                request,
                authentication
        );
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(
            @PathVariable Long id,
            Authentication authentication
    ) {

        return vehicleService.deleteVehicle(
                id,
                authentication
        );
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {

        return vehicleService.getAllVehicles();
    }

    @GetMapping("/my")
    public List<Vehicle> getMyVehicles(
            Authentication authentication
    ) {

        return vehicleService.getMyVehicles(
                authentication
        );
    }
}