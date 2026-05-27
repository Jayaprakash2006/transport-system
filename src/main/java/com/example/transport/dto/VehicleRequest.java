package com.example.transport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleRequest {

    private String vehicleNumber;

    private String vehicleName;

    private String vehicleType;

    private String brand;

    private Double pricePerDay;
}