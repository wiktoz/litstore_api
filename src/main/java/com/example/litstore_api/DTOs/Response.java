package com.example.litstore_api.DTOs;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {
    private Boolean success;
    private String message;
}
