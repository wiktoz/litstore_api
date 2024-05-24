package com.example.litstore_api.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    AUTH_INVALID("Invalid credentials"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User with this e-mail already exists"),
    PASSWORD_SHORT("Password is too short. Minimum 8 characters."),
    PASSWORD_WEAK("Password is too weak"),
    ERR_INVALID_INPUT("Invalid input provided"),
    ERR_DATABASE("Database connection error"),
    ERR_PERMISSION_DENIED("Permission denied");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
