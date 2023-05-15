package com.as.project.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.as.project.dto.AdminUserBookDto;
import com.as.project.dto.AppResponse;
import com.as.project.dto.FeedbackDto;
import com.as.project.dto.LoginDto;
import com.as.project.dto.UserBookingDto;
import com.as.project.dto.UserDto;
import com.as.project.service.UserService;


import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserService service;

    //create new user

    @CrossOrigin
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createNewUser(@Valid @RequestBody UserDto dto)
    {
       final Integer sts= service.createNewUser(dto);

       final AppResponse<Integer> response= AppResponse.<Integer>builder()
                                                   .sts("success")
                                                   .msg("User created Succesfully")
                                                   .bd(sts)
                                                   .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    //get all user

    @CrossOrigin
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserDto>>> allUsers() {
        List<UserDto> users = service.all();

        AppResponse<List<UserDto>> response = AppResponse.<List<UserDto>>builder()
                                                            .sts("success")
                                                            .msg("users")
                                                            .bd(users)
                                                            .build();

        return ResponseEntity.ok().body(response);
    }


    //delete user

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteUser(@PathVariable Long id) {

        final Integer sts = service.deleteUser(id);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
            .sts("success")
            .msg("User Deleted Successfully")
            .bd(sts)
            .build();

        return ResponseEntity.status(200).body(response);
    }

    //update user

    @PutMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> updateUser(@RequestBody UserDto dto) {

        final Integer sts = service.updateUser(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                                                    .sts("success")
                                                    .msg("User Updated Successfully")
                                                    .bd(sts)
                                                    .build();

        return ResponseEntity.ok().body(response);
    }





    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse<UserDto>> getUserById(@PathVariable Long id) {

        final UserDto dto = service.fetchUserDetails(id);

        final AppResponse<UserDto> response = AppResponse.<UserDto>builder()
                                                        .sts("success")
                                                        .msg("User Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }

    //login

    @CrossOrigin
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse<UserDto>> login(@Valid @RequestBody LoginDto dto) {
        final UserDto resDto = service.login(dto);

        AppResponse<UserDto> res = AppResponse.<UserDto>builder()
                                                .sts("success")
                                                .msg("Login Success")
                                                .bd(resDto)
                                                .build();

        return ResponseEntity.ok().body(res);
    }

    @PostMapping(value = "/{userId}/userbookings/{bookingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> bookReservation(@Valid @PathVariable Long userId, @PathVariable Long bookingId) {
        Integer bookEvent = service.bookReservation(userId, bookingId);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .msg("new reservation booked successfully.")
                .bd(bookEvent)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping(value = "/getuserbookings/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserBookingDto>> findAll(@PathVariable Long userId) {

        return ResponseEntity.ok().body(service.getAllBookings(userId));
    }


    @GetMapping(value = "/getcurrentbookings/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserBookingDto>>> getCurrentBookings(@PathVariable Long userId) {
        List<UserBookingDto> sts=service.getCurrentBookings(userId);
        AppResponse<List<UserBookingDto>> response=AppResponse.<List<UserBookingDto>>builder()
                    .sts("success")
                    .msg("All current bookings")
                    .bd(sts)
                    .build();
              return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/getbookinghistory/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserBookingDto>>> getBookingHistory(@PathVariable Long userId) {
        List<UserBookingDto> sts=service.getBookingHistory(userId);
        AppResponse<List<UserBookingDto>> response=AppResponse.<List<UserBookingDto>>builder()
                    .sts("success")
                    .msg("All bookings history")
                    .bd(sts)
                    .build();
              return ResponseEntity.ok().body(response);
    }


    @GetMapping(value = "/alluserbookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<AdminUserBookDto>>> getAllUserBookings() {
        List<AdminUserBookDto> sts = service.getAllUserBookings();
        AppResponse<List<AdminUserBookDto>> response = AppResponse.<List<AdminUserBookDto>>builder()
        .sts("success")
        .msg("All User Bookings")
        .bd(sts)
        .build();
          return ResponseEntity.ok().body(response);
    }

    


    @PostMapping(value = "/{userId}/feedback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createFeedback(@Valid @PathVariable Long userId, @RequestBody FeedbackDto dto) {
        Integer sts = service.createFeedback(userId,dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("feedback submitted.")
                .bd(sts)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<FeedbackDto>>> getAllFeedbacks() {
        List<FeedbackDto> sts = service.listAllFeedbacks();
        AppResponse<List<FeedbackDto>> response = AppResponse.<List<FeedbackDto>>builder()
                .sts("success")
                .msg("All Feedbacks")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }


    
}
