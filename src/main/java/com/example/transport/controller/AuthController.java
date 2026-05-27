package com.example.transport.controller;

import com.example.transport.dto.LoginRequest;
import com.example.transport.dto.RegisterRequest;
import com.example.transport.service.AuthService;
import com.example.transport.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}