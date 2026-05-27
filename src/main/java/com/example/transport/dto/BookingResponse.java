package com.example.transport.dto;

import com.example.transport.model.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingResponse {

    private Long bookingId;

    private String customerName;

    private String vehicleName;

    private String vehicleNumber;

    private LocalDate startDate;

    private LocalDate endDate;

    private BookingStatus status;
}