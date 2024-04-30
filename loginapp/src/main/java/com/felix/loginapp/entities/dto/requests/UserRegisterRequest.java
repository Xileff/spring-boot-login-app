package com.felix.loginapp.entities.dto.requests;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name, username, email, password;
}
