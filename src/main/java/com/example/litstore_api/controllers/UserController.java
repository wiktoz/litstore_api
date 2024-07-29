package com.example.litstore_api.controllers;

import com.example.litstore_api.DTOs.Response;
import com.example.litstore_api.DTOs.user.*;
import com.example.litstore_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<SelfUserResponse> getSelfUser(@RequestHeader("Authorization") String token) {
        SelfUserRequest req = new SelfUserRequest(token);

        return ResponseEntity.ok(userService.getSelfUser(req));
    }

    @GetMapping("/me/addresses")
    public ResponseEntity<SelfAddressResponse> getSelfAddresses(@RequestHeader("Authorization") String token){
        SelfAddressRequest req = new SelfAddressRequest(token);

        return ResponseEntity.ok(userService.getSelfAddresses(req));
    }

    @PostMapping("/me/addresses")
    public ResponseEntity<Response> addSelfAddress(@RequestHeader("Authorization") String token, @RequestBody AddSelfAddressRequest req){
        req.setToken(token);
        return ResponseEntity.ok(userService.addSelfAddress(req));
    }

}
