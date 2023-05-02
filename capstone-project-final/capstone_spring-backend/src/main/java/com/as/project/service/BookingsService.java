package com.as.project.service;

import java.util.List;

import com.as.project.dto.BookingsDto;
import com.as.project.exception.UserNotFoundException;

public interface BookingsService {
    
    Integer createNewBooking(BookingsDto dto);

    List<BookingsDto> all();

    Integer deleteBooking(Long bookingId) throws UserNotFoundException;

    Integer updateBooking(BookingsDto dto);
}
