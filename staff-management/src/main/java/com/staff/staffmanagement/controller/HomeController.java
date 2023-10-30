package com.staff.staffmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Staff Management Microservice!");
    }
}