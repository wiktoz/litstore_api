package com.example.litstore_api.services;

import com.example.litstore_api.exceptions.ApiException;
import com.example.litstore_api.exceptions.ErrorCode;
import com.example.litstore_api.models.User;
import com.example.litstore_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(Integer id){
        User user = this.getUserById(id);
        return user;
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.USER_NOT_FOUND)
        );
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ApiException(ErrorCode.USER_NOT_FOUND)
        );
    }
}
