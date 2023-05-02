package com.as.project.util;

import org.springframework.stereotype.Component;

import com.as.project.domain.Bookings;
import com.as.project.dto.BookingsDto;


@Component
public class BookingsMapper {
    
    public Bookings toDomain(BookingsDto dto)
    {
        return new Bookings(dto.getBookingId(),dto.getBookingDestination(),dto.getDate(),dto.getBookingFrom(),dto.getBookingVname());
        
    }

   public BookingsDto toDto(Bookings domain)
    {
        return new BookingsDto(domain.getBookingId(),domain.getBookingDestination(),domain.getBookingFrom(),domain.getBookingVname(),domain.getDate());
    }
}
