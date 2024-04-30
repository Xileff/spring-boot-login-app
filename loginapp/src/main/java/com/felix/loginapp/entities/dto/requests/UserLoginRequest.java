package com.felix.loginapp.entities.dto.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username, password;
}
