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


import com.as.project.dto.AppResponse;
import com.as.project.dto.LoginDto;
import com.as.project.dto.UserDto;
import com.as.project.service.UserService;


import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserService service;

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

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<String>> loginUser(@Valid @RequestBody LoginDto dto) {
         String sts = service.loginUser(dto);
        
        final AppResponse<String> response = AppResponse.<String>builder()
                                                    .sts("success")
                                                    .msg("login Successfully")
                                                    .bd(sts)
                                                    .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

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

    @CrossOrigin
    @PostMapping(value = "/loginn", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse<UserDto>> login(@Valid @RequestBody LoginDto dto) {
        final UserDto resDto = service.login(dto);

        AppResponse<UserDto> res = AppResponse.<UserDto>builder()
                                                .sts("success")
                                                .msg("Login Success")
                                                .bd(resDto)
                                                .build();

        return ResponseEntity.ok().body(res);
    }

    
}
