package com.as.project.util;


import org.springframework.stereotype.Component;

import com.as.project.domain.User;
import com.as.project.dto.UserDto;

@Component
public class UserMapper {
    
   public User toDomain(UserDto dto)
    {
        return new User(dto.getId(),dto.getUname(),dto.getEmail(),dto.getPassword());
        
    }

   public UserDto toDto(User domain)
    {
        return new UserDto(domain.getId(),domain.getUname(),domain.getEmail(),domain.getPassword());
    }
}
