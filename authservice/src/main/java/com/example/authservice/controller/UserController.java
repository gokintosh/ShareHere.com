package com.example.authservice.controller;


import com.example.authservice.dto.ApiResponse;
import com.example.authservice.dto.SignUpRequest;
import com.example.authservice.entity.Profile;
import com.example.authservice.entity.User;
import com.example.authservice.exception.BadRequestException;
import com.example.authservice.exception.EmailAlreadyExistsException;
import com.example.authservice.exception.UsernameAlreadyExistsException;
import com.example.authservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signup",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload){

        User user= User.builder().username(payload.getUsername())
                .email(payload.getEmail()).password(payload.getPassword()).profile(Profile.builder().displayName(payload.getName()).build()).build();

        try{
            userService.registerUser(user);
        }catch(UsernameAlreadyExistsException | EmailAlreadyExistsException e){
            throw new BadRequestException(e.getMessage());
        }

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true,"user registered successfully"));
//        todo : user service start


    }
}
