package com.example.litstore_api.controllers;

import com.example.litstore_api.DTOs.auth.LoginRequest;
import com.example.litstore_api.DTOs.auth.LoginResponse;
import com.example.litstore_api.DTOs.auth.RegisterRequest;
import com.example.litstore_api.DTOs.auth.RegisterResponse;
import com.example.litstore_api.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
        return ResponseEntity.ok(authService.login(req));
    }
}
