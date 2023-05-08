package com.as.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.project.dto.AppResponse;
import com.as.project.dto.BookingsDto;
import com.as.project.service.BookingsService;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/booking")
@RestController
public class BookingsController {
    
    private final BookingsService service;

    @CrossOrigin
    @PostMapping(value = "/createNewBookings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createNewBookings(@RequestBody BookingsDto dto)
    {
       final Integer sts= service.createNewBooking(dto);

       final AppResponse<Integer> response= AppResponse.<Integer>builder()
                                                   .sts("success")
                                                   .msg("Booking created Succesfully")
                                                   .bd(sts)
                                                   .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @CrossOrigin
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<BookingsDto>>> allBooking() {
        List<BookingsDto> booking = service.all();

        AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                            .sts("success")
                                                            .msg("users")
                                                            .bd(booking)
                                                            .build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/delete/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteBooking(@PathVariable Long bookingId) {

        final Integer sts = service.deleteBooking(bookingId);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
            .sts("success")
            .msg("booking Deleted Successfully")
            .bd(sts)
            .build();

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping(value = "/updateBooking", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> updateBooking(@RequestBody BookingsDto dto) {

        final Integer sts = service.updateBooking(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                                                    .sts("success")
                                                    .msg("booking Updated Successfully")
                                                    .bd(sts)
                                                    .build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/getbookingbyid/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse<BookingsDto>> getBookingById(@PathVariable Long bookingId) {

        final BookingsDto dto = service.fetchBookingDetails(bookingId);

        final AppResponse<BookingsDto> response = AppResponse.<BookingsDto>builder()
                                                        .sts("success")
                                                        .msg("booking Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/filterByFrom/{bookingFrom}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse< List<BookingsDto>>> findByBookingFrom(@PathVariable String bookingFrom) {

        List<BookingsDto> dto = service.findByBookingFrom(bookingFrom);

        final AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                        .sts("success")
                                                        .msg("booking Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterByDestination/{bookingDestination}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse< List<BookingsDto>>> findByBookingDestination(@PathVariable String bookingDestination) {

        List<BookingsDto> dto = service.findByBookingDestination(bookingDestination);

        final AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                        .sts("success")
                                                        .msg("booking Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/filterByFD/{bookingFrom}/{bookingDestination}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse< List<BookingsDto>>> findByBookingFrom(@PathVariable String bookingFrom,@PathVariable String bookingDestination) {

        List<BookingsDto> dto = service.findByBookingFromAndBookingDestination(bookingFrom,bookingDestination);

        final AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                        .sts("success")
                                                        .msg("booking Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterByFDT/{bookingFrom}/{bookingDestination}/{date}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse< List<BookingsDto>>> findByBookingFromAndBookingDestinationAndDate(@PathVariable String bookingFrom,@PathVariable String bookingDestination,@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<BookingsDto> dto = service.findByBookingFromAndBookingDestinationAndDate(bookingFrom,bookingDestination,date);

        final AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                        .sts("success")
                                                        .msg("booking Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/filterByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse< List<BookingsDto>>> findByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<BookingsDto> dto = service.findByDate(date);

        final AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                        .sts("success")
                                                        .msg("booking Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }

}
