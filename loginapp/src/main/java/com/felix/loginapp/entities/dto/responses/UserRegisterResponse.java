package com.felix.loginapp.entities.dto.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegisterResponse {
    private final String username, message;
}
