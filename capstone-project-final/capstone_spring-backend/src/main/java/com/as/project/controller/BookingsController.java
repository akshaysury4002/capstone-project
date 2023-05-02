package com.as.project.controller;

import java.util.List;

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
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<BookingsDto>>> allBooking() {
        List<BookingsDto> users = service.all();

        AppResponse<List<BookingsDto>> response = AppResponse.<List<BookingsDto>>builder()
                                                            .sts("success")
                                                            .msg("users")
                                                            .bd(users)
                                                            .build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteBooking(@PathVariable Long bookingId) {

        final Integer sts = service.deleteBooking(bookingId);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
            .sts("success")
            .msg("Invoice Deleted Successfully")
            .bd(sts)
            .build();

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> updateBooking(@RequestBody BookingsDto dto) {

        final Integer sts = service.updateBooking(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                                                    .sts("success")
                                                    .msg("Invoice Updated Successfully")
                                                    .bd(sts)
                                                    .build();

        return ResponseEntity.ok().body(response);
    }

}
