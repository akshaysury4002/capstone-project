package com.as.project.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class UserBookingDto {
    
    private long id;

    private String bookingVname;

    private LocalDate date;

    private String bookingFrom;

    private String bookingDestination;

    private LocalTime time;

    private String typeVahi;

    private long ttimeTAke;
    
    private Double price;

}
