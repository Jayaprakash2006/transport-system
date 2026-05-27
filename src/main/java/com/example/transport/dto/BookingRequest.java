package com.example.transport.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {

    private Long vehicleId;

    private LocalDate startDate;

    private LocalDate endDate;
}