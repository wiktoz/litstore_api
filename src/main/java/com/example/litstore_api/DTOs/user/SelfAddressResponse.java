package com.example.litstore_api.DTOs.user;

import com.example.litstore_api.DTOs.Response;
import com.example.litstore_api.models.Address;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SelfAddressResponse extends Response {
    private List<Address> addresses;

    @Builder
    public SelfAddressResponse(List<Address> addresses, Boolean success, String message) {
        super(success, message);
        this.addresses = addresses;
    }
}
