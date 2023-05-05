package com.as.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.as.project.domain.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    
    List<Bookings> findByBookingFrom(String bookingFrom);

    List<Bookings> findByBookingFromAndBookingDestination(String bookingFrom, String bookingDestination);

    
}
