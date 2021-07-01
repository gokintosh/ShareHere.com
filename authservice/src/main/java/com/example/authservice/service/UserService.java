package com.example.authservice.service;

import com.example.authservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public User registerUser(User user) {
        log.info("Registering user ",user.getUsername());

        if(user)
        return null;
    }
}
