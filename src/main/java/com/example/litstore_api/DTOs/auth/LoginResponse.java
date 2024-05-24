package com.example.litstore_api.DTOs.auth;

import com.example.litstore_api.DTOs.Response;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends Response {
    private String token;

    @Builder
    public LoginResponse(String token, Boolean success, String message) {
        super(success, message);
        this.token = token;
    }
}
