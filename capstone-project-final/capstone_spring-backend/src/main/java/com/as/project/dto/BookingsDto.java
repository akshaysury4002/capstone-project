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
public class BookingsDto {

    private long bookingId;
   
    @NotEmpty(message = "time cant be empty")
    @NotNull(message = "time cant be null")
    @NotBlank(message = "time cant be blank")
    private String bookingVname;

    @NotEmpty(message = "time cant be empty")
    @NotNull(message = "time cant be null")
    @NotBlank(message = "time cant be blank")
    private LocalDate date;
    
    @NotEmpty(message = "time cant be empty")
    @NotNull(message = "time cant be null")
    @NotBlank(message = "time cant be blank")
    private String bookingFrom;
    
    @NotEmpty(message = "time cant be empty")
    @NotNull(message = "time cant be null")
    @NotBlank(message = "time cant be blank")
    private String bookingDestination;

    @NotEmpty(message = "time cant be empty")
    @NotNull(message = "time cant be null")
    @NotBlank(message = "time cant be blank")
    private LocalTime time;

    @NotEmpty(message = "TypeVahi cant be empty")
    @NotNull(message = "TypeVahi cant be null")
    @NotBlank(message = "TypeVahi cant be blank")
    private String typeVahi;


    @NotEmpty(message = "ttimeTAke cant be empty")
    @NotNull(message = "ttimeTAke cant be null")
    @NotBlank(message = "ttimeTAke cant be blank")
    private long ttimeTAke;

    @NotEmpty(message = "price cant be empty")
    @NotNull(message = "price cant be null")
    @NotBlank(message = "price cant be blank")
    private double price;
    
    
}
