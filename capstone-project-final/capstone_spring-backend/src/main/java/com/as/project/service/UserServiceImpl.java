package com.as.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.project.domain.Bookings;
import com.as.project.domain.User;
import com.as.project.dto.CreateNewUserBookingDto;
import com.as.project.dto.LoginDto;
import com.as.project.dto.UserDto;
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

    private final BookingsRepository bookingRepository;
    private final UserRepository repository;
    private final UserMapper mapper;
    private final DynamicMapper dynamicMapper;
    

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
    public Integer createNewUserBooking(CreateNewUserBookingDto dto) {
        User user = repository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No Id found"));
        Bookings bookings = new Bookings();
        BeanUtils.copyProperties(dto, bookings);
        bookings.getUser().add(user);
        bookingRepository.save(bookings);
        return 1;

    }

   
}
