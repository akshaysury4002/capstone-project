package com.as.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.project.domain.User;
import com.as.project.dto.UserDto;
import com.as.project.exception.UserNotFoundException;
import com.as.project.repository.UserRepository;
import com.as.project.util.UserMapper;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

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
    
}
