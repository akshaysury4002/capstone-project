package com.as.project.util;

import org.springframework.stereotype.Component;

import com.as.project.domain.Bookings;
import com.as.project.dto.BookingsDto;


@Component
public class BookingsMapper {
    
    public Bookings toDomain(BookingsDto dto)
    {
        return new Bookings(dto.getBookingId(), dto.getBookingVname(), dto.getDate(), dto.getBookingFrom(), dto.getBookingDestination(), dto.getTime(), dto.getTypeVahi(), dto.getTtimeTAke(), dto.getPrice());
        
    }

   public BookingsDto toDto(Bookings domain)
    {
        return new BookingsDto(domain.getBookingId(),domain.getBookingVname(),domain.getDate(),domain.getBookingFrom(),domain.getBookingDestination(),domain.getTime(),domain.getTypeVahi(),domain.getTtimeTAke(),domain.getPrice());
    }
}
