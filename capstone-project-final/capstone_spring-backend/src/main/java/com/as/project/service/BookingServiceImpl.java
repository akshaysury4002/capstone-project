package com.as.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.project.domain.Bookings;
import com.as.project.dto.BookingsDto;
import com.as.project.exception.BookingNotFoundException;
import com.as.project.repository.BookingsRepository;
import com.as.project.util.BookingsMapper;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingsService {

    private final BookingsRepository repository;
    private final BookingsMapper mapper;

    @Override
    public Integer createNewBooking(BookingsDto dto) {
        repository.save(mapper.toDomain(dto));
        return 1;

    }

    @Override
    public List<BookingsDto> all() {
        return repository.findAll()
                         .stream()
                      // .map( invoice -> mapper.toDto(invoice) )
                         .map(mapper::toDto)
                         .collect(Collectors.toList());
    }
    @Override
    public Integer deleteBooking(Long bookingId) {
        repository.deleteById(bookingId);
        return 1;
    }
    @Override
    public Integer updateBooking(BookingsDto bookings) {
        repository.save(mapper.toDomain(bookings));
       return 1;
    }

    @Override
    public BookingsDto fetchBookingDetails(Long bookingId) throws BookingNotFoundException {
        Optional<Bookings> op = repository.findById(bookingId);
        return mapper.toDto(op.orElseThrow(() -> new BookingNotFoundException("Booking " + bookingId + " Not Found")));
    }

    @Override
    public List<BookingsDto> findByBookingFrom(String bookingFrom) {
        return repository.findByBookingFrom(bookingFrom)
                         .stream()
                         .map(mapper::toDto)
                         .collect(Collectors.toList());
    }

    @Override
    public List<BookingsDto> findByBookingDestination(String bookingDestination) {

        
        return repository.findByBookingDestination(bookingDestination)
                         .stream()
                         .map(mapper::toDto)
                         .collect(Collectors.toList());
    }

    @Override
    public List<BookingsDto> findByBookingFromAndBookingDestination(String bookingFrom, String bookingDestination) {
       
        return repository.findByBookingFromAndBookingDestination(bookingFrom,bookingDestination)
                         .stream()
                         .map(mapper::toDto)
                         .collect(Collectors.toList());
    }

    @Override
    public List<BookingsDto> findByBookingFromAndBookingDestinationAndDate(String bookingFrom,String bookingDestination, LocalDate date) {
        return repository.findByBookingFromAndBookingDestinationAndDate(bookingFrom,bookingDestination,date)
                         .stream()
                         .map(mapper::toDto)
                         .collect(Collectors.toList());
    }

    @Override
    public List<BookingsDto> findByDate(LocalDate date) {
        return repository.findByDate(date)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
    }


}
