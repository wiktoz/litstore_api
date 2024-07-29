package com.example.litstore_api.DTOs.user;

import com.example.litstore_api.DTOs.Response;
import com.example.litstore_api.models.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SelfUserResponse extends Response {
    private User user;

    @Builder
    public SelfUserResponse(User user, Boolean success, String message) {
        super(success, message);
        this.user = user;
    }
}
