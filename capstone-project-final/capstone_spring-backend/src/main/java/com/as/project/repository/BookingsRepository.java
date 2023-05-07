package com.as.project.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.as.project.domain.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    
    List<Bookings> findByBookingFrom(String bookingFrom);

    List<Bookings> findByBookingDestination(String bookingDestination);

    List<Bookings> findByBookingFromAndBookingDestination(String bookingFrom, String bookingDestination);

    List<Bookings> findByBookingFromAndBookingDestinationAndDate(String bookingFrom, String bookingDestination,LocalDate date);

    
}
