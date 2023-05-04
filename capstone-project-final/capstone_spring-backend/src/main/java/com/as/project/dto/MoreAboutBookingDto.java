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
public class MoreAboutBookingDto {

    private long bookingId;
   
    @NotEmpty(message = "bookingVname cant be empty")
    @NotNull(message = "bookingVname cant be null")
    @NotBlank(message = "bookingVname cant be blank")
    private String bookingVname;
    
    @NotEmpty(message = "bookingFrom cant be empty")
    @NotNull(message = "bookingFrom cant be null")
    @NotBlank(message = "bookingFrom cant be blank")
    private String bookingFrom;
    
    @NotEmpty(message = "bookingDestination cant be empty")
    @NotNull(message = "bookingDestination cant be null")
    @NotBlank(message = "bookingDestination cant be blank")
    private String bookingDestination;

    @NotEmpty(message = "date cant be empty")
    @NotNull(message = "date cant be null")
    @NotBlank(message = "date cant be blank")
    private LocalDate date;

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
    private int ttimeTAke;
    

    
}
