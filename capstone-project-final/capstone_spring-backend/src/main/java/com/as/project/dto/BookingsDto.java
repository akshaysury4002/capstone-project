package com.as.project.dto;

import java.time.LocalDate;

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
   
    private String bookingVname;
    
    private String bookingFrom;
    
    private String bookingDestination;

    private LocalDate date;
    
}
