package com.example.transport.controller;

import com.example.transport.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        return "Welcome " + user.getName()
                + " | Role: " + user.getRole();
    }
}