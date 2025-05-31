package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.request.CreateAccountRequest;
import com.qnadeel.springdemo.security.management.dto.request.LoginRequest;
import com.qnadeel.springdemo.security.management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authentication") // class level
public class AuthenticationController {

    private final UserService userService;

    @Operation(summary = "Register new user",
            description = "Creates a new user in the system")
    @PostMapping("/registration")
    public ResponseEntity<String> creteAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        userService.creteAccount(createAccountRequest);
        return ResponseEntity.ok("Account Created");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        userService.userLogin(loginRequest);
        return ResponseEntity.ok("Login Successful");
    }
}