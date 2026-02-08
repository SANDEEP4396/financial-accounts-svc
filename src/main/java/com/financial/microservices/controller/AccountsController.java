package com.financial.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to home page";
    }

    @GetMapping("/customerDetails")
    public String customerDetails() {
        return "Welcome to customer details page";
    }
}
