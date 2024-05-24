package com.example.litstore_api.security;

import com.example.litstore_api.exceptions.ApiException;
import com.example.litstore_api.exceptions.ErrorCode;

public class PasswordTester {
    private final String password;

    public PasswordTester(String password){
        this.password = password;
    }
    public boolean test(){
        if(password.length() < 8){
            throw new ApiException(ErrorCode.PASSWORD_SHORT);
        }

        return true;
    }
}
