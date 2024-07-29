package com.example.litstore_api.DTOs.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSelfAddressRequest {
    private String token;
    private String name;
    private String surname;
    private String street;
    private String house;
    private String flat;
    private String post_code;
    private String city;
    private String country;
    private String phone;
}
