package com.example.litstore_api.services;

import com.example.litstore_api.DTOs.Response;
import com.example.litstore_api.DTOs.user.*;
import com.example.litstore_api.exceptions.ApiException;
import com.example.litstore_api.exceptions.ErrorCode;
import com.example.litstore_api.models.Address;
import com.example.litstore_api.models.User;
import com.example.litstore_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    // /api/users/me GET PUT DELETE
    public SelfUserResponse getSelfUser(SelfUserRequest req){
        String email = jwtService.extractEmail(req.getToken());

        User u = userRepository.findByEmail(email).orElseThrow(
                () -> new ApiException(ErrorCode.USER_NOT_FOUND));

        return SelfUserResponse.builder()
                .success(true)
                .message("")
                .user(u)
                .build();
    }

    public User updateSelfUser(String token){
        String email = jwtService.extractEmail(token);
        return null;
    }

    public User deleteSelfUser(){
        return null;
    }

    // /api/users/me/addresses GET POST
    public SelfAddressResponse getSelfAddresses(SelfAddressRequest req){
        String email = jwtService.extractEmail(req.getToken());
        User u = userRepository.findByEmail(email).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        List<Address> addresses = userRepository.getUserAddresses(u.getUser_id());

        return SelfAddressResponse.builder()
                .success(true)
                .message("")
                .addresses(addresses)
                .build();
    }

    public Response addSelfAddress(AddSelfAddressRequest req){
        String email = jwtService.extractEmail(req.getToken());
        User u = userRepository.findByEmail(email).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND));

        Address address = new Address();
        address.setUser_id(u.getUser_id());
        address.setName(req.getName());
        address.setSurname(req.getSurname());
        address.setStreet(req.getStreet());
        address.setHouse(req.getHouse());
        address.setFlat(req.getFlat());
        address.setPost_code(req.getPost_code());
        address.setCity(req.getCity());

        return Response.builder()
                .success(true)
                .message("Successfully added address")
                .build();
    }

    // /api/users/me/addresses/id/{id} GET PUT DELETE
    public Address getSelfAddressById(Integer addressId){
        return null;
    }

    public Address updateSelfAddressById(Integer addressId){
        return null;
    }

    public Address deleteSelfAddressById(Integer addressId){
        return null;
    }

    // admin only \/

    // /api/users GET
    public List<User> getAllUsers(){
        return null;
    }

    // /api/users/id/{id} GET PUT DELETE
    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(
                () -> new ApiException(ErrorCode.USER_NOT_FOUND)
        );
    }

    public User updateUserById(){
        return null;
    }

    public Response deleteUserById(String userId){
        return null;
    }

    // /api/users/id/{id}/addresses GET POST
    public Address getAllUserAddresses(String userId){
        return null;
    }

    public Address addUserAddress(String userId) { return null; }

    // /api/users/id/{id}/addresses/id/{id} GET PUT DELETE
    public Address getUserAddressById(String userId, String addressId) { return null; }
    public Address updateUserAddressById(String userId, String addressId) { return null; }
    public Address deleteUserAddressById(String userId, String addressId) { return null; }

    // /api/users/search/{phrase} GET
    public User searchUser(String phrase) { return null; }

    // /api/users/{id}/roles GET POST DELETE
    public User getUserRoles(String userId) { return null; }
    public User addUserRole(String userId) { return null; }
    public User deleteUserRole(String userId) { return null; }

    // /api/users/{id}/permissions GET POST DELETE
    public User getUserPermissions(String userId) { return null; }
    public User addUserPermission(String userId) { return null; }
    public User deleteUserPermission(String userId) { return null; }
}
