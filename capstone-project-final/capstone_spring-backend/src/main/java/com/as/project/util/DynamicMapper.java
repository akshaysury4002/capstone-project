package com.as.project.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.as.project.domain.User;
import com.as.project.dto.LoginDto;

@Component
public class DynamicMapper {
    
    public <T, U> U convertor(T entity, U dto) {
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public User toDomain(LoginDto dto){
        User user=new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

}
