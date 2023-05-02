package com.as.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.as.project.dto.BookingsDto;
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

   
    
}
