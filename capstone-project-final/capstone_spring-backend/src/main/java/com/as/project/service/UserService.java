package com.as.project.service;


import java.util.List;

import com.as.project.dto.LoginDto;
import com.as.project.dto.UserDto;
import com.as.project.exception.UserNotFoundException;

public interface UserService {
    
    Integer createNewUser(UserDto user);

    List<UserDto> all();

    Integer deleteUser(Long id) throws UserNotFoundException;

    Integer updateUser(UserDto user);

    UserDto fetchUserDetails(Long id) throws UserNotFoundException;
    
    String loginUser (LoginDto dto) throws UserNotFoundException ;

    UserDto login(LoginDto dto) throws UserNotFoundException;
}
