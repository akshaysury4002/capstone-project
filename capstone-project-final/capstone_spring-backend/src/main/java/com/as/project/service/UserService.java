package com.as.project.service;


import java.util.List;

import com.as.project.dto.AdminUserBookDto;

import com.as.project.dto.FeedbackDto;
import com.as.project.dto.LoginDto;
import com.as.project.dto.UserBookingDto;
import com.as.project.dto.UserDto;
import com.as.project.exception.UserNotFoundException;

public interface UserService {
    
    Integer createNewUser(UserDto user);

    List<UserDto> all();

    Integer deleteUser(Long id) throws UserNotFoundException;

    Integer updateUser(UserDto user);

    UserDto fetchUserDetails(Long id) throws UserNotFoundException;

    UserDto login(LoginDto dto) throws UserNotFoundException;

    Integer bookReservation(Long userId,Long bookingId);

    List<UserBookingDto> getAllBookings(Long userId);

    List<UserBookingDto> getCurrentBookings(Long userId);

    List<UserBookingDto> getBookingHistory(Long userId);

    Integer createFeedback(Long id, FeedbackDto dto);

    List<FeedbackDto> listAllFeedbacks();

    List<AdminUserBookDto> getAllUserBookings();

    Integer deleteUserBooking(Long bookingId, Long userId);
    

}
