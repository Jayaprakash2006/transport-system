package com.example.transport.service;

import com.example.transport.dto.AdminBookingResponse;
import com.example.transport.dto.BookingRequest;
import com.example.transport.dto.BookingResponse;
import com.example.transport.model.Booking;
import com.example.transport.model.BookingStatus;
import com.example.transport.model.User;
import com.example.transport.model.Vehicle;
import com.example.transport.repository.BookingRepository;
import com.example.transport.repository.UserRepository;
import com.example.transport.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public String bookVehicle(
            BookingRequest request,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        Vehicle vehicle = vehicleRepository
                .findById(request.getVehicleId())
                .orElse(null);

        if (vehicle == null) {
            return "Vehicle not found";
        }

        boolean alreadyBooked = bookingRepository.isVehicleBooked(
                vehicle,
                request.getStartDate(),
                request.getEndDate()
        );

        if (alreadyBooked) {
            return "Vehicle already booked for selected dates";
        }


        Booking booking = new Booking();

        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());

        booking.setStatus(BookingStatus.PENDING);

        booking.setUser(user);

        booking.setVehicle(vehicle);


        bookingRepository.save(booking);


        return "Vehicle booked successfully";
    }

    public List<BookingResponse> getMyBookings(
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        List<Booking> bookings =
                bookingRepository.findByUser(user);

        List<BookingResponse> responseList =
                new ArrayList<>();

        for (Booking booking : bookings) {

            BookingResponse response =
                    new BookingResponse();

            response.setBookingId(booking.getId());

            response.setCustomerName(
                    booking.getUser().getName()
            );

            response.setVehicleName(
                    booking.getVehicle().getVehicleName()
            );

            response.setVehicleNumber(
                    booking.getVehicle().getVehicleNumber()
            );

            response.setStartDate(
                    booking.getStartDate()
            );

            response.setEndDate(
                    booking.getEndDate()
            );

            response.setStatus(
                    booking.getStatus()
            );

            responseList.add(response);
        }

        return responseList;
    }

    public String cancelBooking(
            Long bookingId,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();

        Booking booking = bookingRepository
                .findById(bookingId)
                .orElse(null);

        if (booking == null) {
            return "Booking not found";
        }

        if (!booking.getUser().getId()
                .equals(user.getId())) {

            return "You cannot cancel this booking";
        }

        if (booking.getStatus() ==
                BookingStatus.CANCELLED) {

            return "Booking already cancelled";
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Vehicle vehicle = booking.getVehicle();


        bookingRepository.save(booking);

        return "Booking cancelled successfully";
    }
    public List<AdminBookingResponse> getAllBookings() {

        List<Booking> bookings =
                bookingRepository.findAll();

        List<AdminBookingResponse> responseList =
                new ArrayList<>();

        for (Booking booking : bookings) {

            AdminBookingResponse response =
                    new AdminBookingResponse();

            response.setBookingId(
                    booking.getId()
            );

            response.setCustomerName(
                    booking.getUser().getName()
            );

            response.setCustomerEmail(
                    booking.getUser().getEmail()
            );

            response.setVehicleName(
                    booking.getVehicle().getVehicleName()
            );

            response.setVehicleNumber(
                    booking.getVehicle().getVehicleNumber()
            );

            response.setStartDate(
                    booking.getStartDate()
            );

            response.setEndDate(
                    booking.getEndDate()
            );

            response.setStatus(
                    booking.getStatus()
            );

            responseList.add(response);
        }

        return responseList;
    }
    public List<BookingResponse> getOwnerBookings(
            Authentication authentication
    ) {

        User owner =
                (User) authentication.getPrincipal();

        List<Booking> bookings =
                bookingRepository.findByVehicleOwner(owner);

        List<BookingResponse> responseList =
                new ArrayList<>();

        for (Booking booking : bookings) {

            BookingResponse response =
                    new BookingResponse();

            response.setBookingId(
                    booking.getId()
            );

            response.setCustomerName(
                    booking.getUser().getName()
            );

            response.setVehicleName(
                    booking.getVehicle().getVehicleName()
            );

            response.setVehicleNumber(
                    booking.getVehicle().getVehicleNumber()
            );

            response.setStartDate(
                    booking.getStartDate()
            );

            response.setEndDate(
                    booking.getEndDate()
            );

            response.setStatus(
                    booking.getStatus()
            );

            responseList.add(response);
        }

        return responseList;
    }
}