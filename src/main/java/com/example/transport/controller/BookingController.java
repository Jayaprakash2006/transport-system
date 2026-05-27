package com.example.transport.controller;

import com.example.transport.dto.AdminBookingResponse;
import com.example.transport.dto.BookingRequest;
import com.example.transport.dto.BookingResponse;
import com.example.transport.model.Booking;
import com.example.transport.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public String bookVehicle(
            @RequestBody BookingRequest request,
            Authentication authentication
    ) {

        return bookingService
                .bookVehicle(request, authentication);
    }
    @GetMapping("/my")
    public List<BookingResponse> getMyBookings(
            Authentication authentication
    ) {

        return bookingService
                .getMyBookings(authentication);
    }
    @PutMapping("/cancel/{bookingId}")
    public String cancelBooking(
            @PathVariable Long bookingId,
            Authentication authentication
    ) {

        return bookingService.cancelBooking(
                bookingId,
                authentication
        );
    }
    @GetMapping("/all")
    public List<AdminBookingResponse> getAllBookings() {

        return bookingService.getAllBookings();
    }
    @GetMapping("/owner")
    public List<BookingResponse> getOwnerBookings(
            Authentication authentication
    ) {

        return bookingService.getOwnerBookings(
                authentication
        );
    }
}