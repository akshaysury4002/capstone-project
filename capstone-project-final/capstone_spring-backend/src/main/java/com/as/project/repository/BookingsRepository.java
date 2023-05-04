package com.as.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.as.project.domain.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    
}
