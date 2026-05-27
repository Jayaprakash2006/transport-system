package com.example.transport.repository;

import com.example.transport.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);

    List<Booking> findByVehicleOwner(User owner);

    @Query("""
SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
FROM Booking b
WHERE b.vehicle = :vehicle
AND b.status = 'PENDING'
AND (
    (b.startDate <= :endDate AND b.endDate >= :startDate)
)
""")
    boolean isVehicleBooked(
            @Param("vehicle") Vehicle vehicle,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}