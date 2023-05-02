package com.as.project.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookingId")
    private long bookingId;

    @Column(name = "vahicle_name")
    private String bookingVname;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "bookingFrom")
    private String bookingFrom;

    @Column(name = "bookingDestination")
    private String bookingDestination;

}

