package com.as.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminUserBookDto {

    @NotEmpty(message = "location cant be empty")
    @NotNull(message = "location cant be null")
    @NotBlank(message = "location cant be blank")
    private String bookingFrom;

    @NotEmpty(message = "location cant be empty")
    @NotNull(message = "location cant be null")
    @NotBlank(message = "location cant be blank")
    private String bookingDestination;

    @NotEmpty(message = "startDate cant be empty")
    @NotNull(message = "startDate cant be null")
    @NotBlank(message = "startDate cant be blank")
    private LocalDate date;

    @NotEmpty(message = "startingTime cant be empty")
    @NotNull(message = "startingTime cant be null")
    @NotBlank(message = "startingTime cant be blank")
    private LocalTime time;
    

    @NotEmpty(message = "price cant be empty")
    @NotNull(message = "price cant be null")
    @NotBlank(message = "price cant be blank")
    private Double price;

    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String uname;

    @NotEmpty(message = "userId cant be empty")
    @NotNull(message = "userId cant be null")
    @NotBlank(message = "userId cant be blank")
    private long userId;
    
}
