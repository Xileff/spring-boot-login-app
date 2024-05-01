package com.felix.loginapp.entities.dto.responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserLoginResponse {
    private final String accessToken;
}
