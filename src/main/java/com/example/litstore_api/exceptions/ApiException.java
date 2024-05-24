package com.example.litstore_api.exceptions;

public class ApiException extends RuntimeException {
    private final ErrorCode code;
    public ApiException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }

    public ErrorCode getErrorCode() {
        return code;
    }
}
