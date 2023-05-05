package com.as.project.service;

import java.util.List;

import com.as.project.dto.BookingsDto;
import com.as.project.exception.BookingNotFoundException;


public interface BookingsService {
    
    Integer createNewBooking(BookingsDto dto);

    List<BookingsDto> all();

    Integer deleteBooking(Long bookingId) throws BookingNotFoundException;

    Integer updateBooking(BookingsDto dto);

    BookingsDto fetchBookingDetails(Long id) throws BookingNotFoundException;

    List<BookingsDto> findByBookingFrom(String bookingFrom);

    List<BookingsDto> findByBookingFromAndBookingDestination(String bookingFrom, String bookingDestination);
}
