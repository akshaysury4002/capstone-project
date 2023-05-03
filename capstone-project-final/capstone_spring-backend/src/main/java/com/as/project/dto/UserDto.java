package com.as.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDto 
{
    private long id;

    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String uname;

    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String email;
    
    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters long")
    private String password;

    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String role;
    
}
