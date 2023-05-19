package com.as.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateUserDto {


    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String uname;

    @NotEmpty(message = "email cant be empty")
    @NotNull(message = "email cant be null")
    @NotBlank(message = "email cant be blank")
    private String email;

    @NotEmpty(message = "role cant be empty")
    @NotNull(message = "role cant be null")
    @NotBlank(message = "role cant be blank")
    private String role;
    
}
