package com.example.transport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    @GetMapping
    public String getString() {
        return "Good Start";
    }

}