package com.staff.staffmanagement.controller;


import com.staff.staffmanagement.auth.AuthenticationRequest;
import com.staff.staffmanagement.auth.AuthenticationResponse;
import com.staff.staffmanagement.auth.RegisterRequest;
import com.staff.staffmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerStaffSimple(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerStaff(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createStaffSimple(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateStaff(request));
    }
}
