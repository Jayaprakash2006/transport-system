package com.example.transport.controller;

import com.example.transport.dto.VehicleRequest;
import com.example.transport.model.Vehicle;
import com.example.transport.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/add")
    public String addVehicle(
            @RequestBody VehicleRequest request
    ) {

        return vehicleService.addVehicle(request);
    }

    @PutMapping("/update/{id}")
    public String updateVehicle(
            @PathVariable Long id,
            @RequestBody VehicleRequest request
    ) {

        return vehicleService.updateVehicle(id, request);
    }

    @DeleteMapping("delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {

        return vehicleService.getAllVehicles();
    }
}