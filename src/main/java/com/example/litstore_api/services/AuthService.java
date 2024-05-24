package com.example.litstore_api.services;

import com.example.litstore_api.DTOs.auth.LoginRequest;
import com.example.litstore_api.DTOs.auth.LoginResponse;
import com.example.litstore_api.DTOs.auth.RegisterRequest;
import com.example.litstore_api.DTOs.auth.RegisterResponse;
import com.example.litstore_api.exceptions.ApiException;
import com.example.litstore_api.exceptions.ErrorCode;
import com.example.litstore_api.models.User;
import com.example.litstore_api.repositories.UserRepository;
import com.example.litstore_api.security.PasswordTester;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public RegisterResponse register(RegisterRequest req){
        if(userRepository.findByEmail(req.getEmail()).isPresent()){
            throw new ApiException(ErrorCode.USER_NOT_FOUND);
        }

        PasswordTester passwordTester = new PasswordTester(req.getPassword());

        if(!passwordTester.test()){
            throw new ApiException(ErrorCode.PASSWORD_WEAK);
        }

        Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

        User user = User.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .success(true)
                .message("Successfully registered")
                .build();
    }

    public LoginResponse login(LoginRequest req){

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        final User user = userRepository.findByEmail(req.getEmail()).orElseThrow(
                () -> new ApiException(ErrorCode.AUTH_INVALID)
        );

        final String token = jwtService.generateToken(user);


        return LoginResponse.builder()
                .token(token)
                .success(true)
                .message("Successfully logged in")
                .build();
    }
}
