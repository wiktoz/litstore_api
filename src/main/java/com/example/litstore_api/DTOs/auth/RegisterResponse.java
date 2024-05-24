package com.example.litstore_api.DTOs.auth;

import com.example.litstore_api.DTOs.Response;
import com.example.litstore_api.models.User;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterResponse extends Response {
    @Builder
    public RegisterResponse(Boolean success, String message) {
        super(success, message);
    }
}
