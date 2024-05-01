package com.felix.loginapp.controllers;

import com.felix.loginapp.entities.dto.ResponseBody;
import com.felix.loginapp.entities.dto.requests.UserLoginRequest;
import com.felix.loginapp.entities.dto.requests.UserRegisterRequest;
import com.felix.loginapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("auth/register")
    public ResponseEntity<ResponseBody> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.insertUser(userRegisterRequest);
    }

    @PostMapping("auth/login")
    public ResponseEntity<ResponseBody> login(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.login(userLoginRequest);
    }

    @GetMapping("user")
    public ResponseEntity<ResponseBody> dashboard() {
        return userService.getDashboard();
    }
}
