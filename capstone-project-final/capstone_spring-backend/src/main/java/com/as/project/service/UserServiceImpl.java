package com.as.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.project.domain.Bookings;
import com.as.project.domain.Feedback;
import com.as.project.domain.User;
import com.as.project.dto.AdminUserBookDto;
import com.as.project.dto.FeedbackDto;
import com.as.project.dto.LoginDto;
import com.as.project.dto.UserBookingDto;
import com.as.project.dto.UserDto;
import com.as.project.exception.BookingNotFoundException;
import com.as.project.exception.DuplicateEventException;
import com.as.project.exception.FeedbackNotFoundException;
import com.as.project.exception.InvalidRoleException;
import com.as.project.exception.UserNotFoundException;
import com.as.project.repository.BookingsRepository;
import com.as.project.repository.FeedbackRepository;
import com.as.project.repository.UserRepository;
import com.as.project.util.DynamicMapper;
import com.as.project.util.UserMapper;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final DynamicMapper dynamicMapper;
    private final BookingsRepository bookingRepo;
    private final FeedbackRepository feedbackRepository;

    @Override
    public Integer createNewUser(UserDto dto) {
        
        repository.save(mapper.toDomain(dto));
        return 1;
    }

    @Override
    public List<UserDto> all() {
        return repository.findAll()
                         .stream()
                        // .map( invoice -> mapper.toDto(invoice) )
                         .map(mapper::toDto)
                         .collect(Collectors.toList());
    }

    @Override
    public Integer deleteUser(Long id) {
       repository.deleteById(id);
       return 1;
    }

    @Override
    public Integer updateUser(UserDto user) {
       repository.save(mapper.toDomain(user));
       return 1;
       
    }

    @Override
    public UserDto fetchUserDetails(Long id) throws UserNotFoundException {
        Optional<User> op = repository.findById(id);
        return mapper.toDto(op.orElseThrow(() -> new UserNotFoundException("Invoice " + id + " Not Found")));
    }

    @Override
    public UserDto login(LoginDto dto) throws UserNotFoundException {
        Optional<User> op = repository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        User user = op.orElseThrow(() -> new UserNotFoundException("User Not Found"));
          
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);

        return userDto;
    }

    @Override
    public Integer bookReservation(Long userId, Long bookingsId) {
        
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't book Event");
        Bookings bookings = bookingRepo.findById(bookingsId)
                .orElseThrow(() -> new BookingNotFoundException("Event not Found for " + bookingsId + " id"));

        if (user.getBookings().contains(bookings))
            throw new DuplicateEventException("Event already booked...");
        user.getBookings().add(bookings);
        repository.save(user);
        return 1;

    }

    @Override
    public List<UserBookingDto> getAllBookings(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("No bookings for Admin");

        List<UserBookingDto> collect = user.getBookings()
                .stream()
                .map(bookings -> dynamicMapper.convertor(bookings, new UserBookingDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new BookingNotFoundException("No bookings found book one.");

        return collect;
    }

    @Override
    public List<UserBookingDto> getCurrentBookings(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("No bookings for Admin");

            LocalDate currentDate = LocalDate.now();

        List<UserBookingDto> collect = user.getBookings().stream()
                                         .filter(booking -> booking.getDate().isAfter(currentDate))
                                         .map(booking -> dynamicMapper.convertor(booking, new UserBookingDto()))
                                         .collect(Collectors.toList());
                
        if (collect.isEmpty())
            throw new BookingNotFoundException("No bookings found book one.");

        return collect;
    }

    @Override
    public List<UserBookingDto> getBookingHistory(Long userId) {

        User user = repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("No bookings for Admin");

            LocalDate currentDate = LocalDate.now();

        List<UserBookingDto> collect = user.getBookings().stream()
                                         .filter(booking -> booking.getDate().isBefore(currentDate))
                                         .map(booking -> dynamicMapper.convertor(booking, new UserBookingDto()))
                                         .collect(Collectors.toList());
                
        if (collect.isEmpty())
            throw new BookingNotFoundException("No bookings found book one.");

        return collect;
    }


    public List<AdminUserBookDto> getAllUserBookings() {
        List<AdminUserBookDto> adminUserBookDtos = new ArrayList<>();
        List<User> users = repository.findAll();
        for (User user : users) {
            for (Bookings bookingSlot : user.getBookings()) {
                AdminUserBookDto adminUserBookDto = new AdminUserBookDto();
                adminUserBookDto.setUserId(user.getId());
                adminUserBookDto.setUname(user.getUname());
                adminUserBookDto.setBookingFrom(bookingSlot.getBookingFrom());
                adminUserBookDto.setBookingDestination(bookingSlot.getBookingDestination());
                adminUserBookDto.setDate(bookingSlot.getDate());
                adminUserBookDto.setTime(bookingSlot.getTime());
                adminUserBookDto.setPrice(bookingSlot.getPrice());
                adminUserBookDtos.add(adminUserBookDto);
            }
        }
        return adminUserBookDtos;
    }





    @Override
    public Integer createFeedback(Long id, FeedbackDto dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not Found for " + id + " id"));

        Feedback feedback = dynamicMapper.convertor(dto, new Feedback());
        feedback.setUser(user);
        feedbackRepository.save(feedback);
        return 1;
    }

    @Override
    public List<FeedbackDto> listAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
    
        for (Feedback feedback : feedbackList) {
            FeedbackDto feedbackDto = new FeedbackDto();
            BeanUtils.copyProperties(feedback, feedbackDto);
            feedbackDtoList.add(feedbackDto);
            if(feedbackDtoList.isEmpty()){
                throw new FeedbackNotFoundException("no feedback present ");
            }
        }
    
        return feedbackDtoList;
    }

    @Override
    public Integer deleteUserBooking(Long bookingId, Long userId) {
        Bookings bookingSlot = bookingRepo.findById(bookingId)
        .orElseThrow(() -> new BookingNotFoundException("No user booking slot found"));
         User user = bookingSlot.getUser().stream().filter(u -> u.getId().equals(userId)).findFirst()
        .orElseThrow(() -> new UserNotFoundException("No user booking slot found"));

      bookingSlot.getUser().remove(user);
      user.getBookings().remove(bookingSlot);
      bookingRepo.save(bookingSlot);
      return 1;
    }


    

}
