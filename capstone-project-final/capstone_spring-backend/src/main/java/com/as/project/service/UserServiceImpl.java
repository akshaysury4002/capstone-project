package com.as.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.project.domain.Bookings;
import com.as.project.domain.User;

import com.as.project.dto.LoginDto;
import com.as.project.dto.UserDto;
import com.as.project.exception.BookingNotFoundException;
import com.as.project.exception.DuplicateEventException;
import com.as.project.exception.InvalidRoleException;
import com.as.project.exception.UserNotFoundException;
import com.as.project.repository.BookingsRepository;
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
    public String loginUser(LoginDto dto) {
    User user=dynamicMapper.toDomain(dto);
    List<User> users = repository.findAll();
    for(User users2:users){
      if(user.getEmail().equals(users2.getEmail())&&user.getPassword().equals(users2.getPassword())){
       
            return users2.getRole();
         }
      }
    return "invalid user";
    
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
   
}
