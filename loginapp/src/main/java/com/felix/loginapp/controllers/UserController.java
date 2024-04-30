package com.felix.loginapp.controllers;

import com.felix.loginapp.entities.dto.requests.UserLoginRequest;
import com.felix.loginapp.entities.dto.requests.UserRegisterRequest;
import com.felix.loginapp.entities.dto.responses.UserLoginResponse;
import com.felix.loginapp.entities.dto.responses.UserRegisterResponse;
import com.felix.loginapp.services.UserService;
import com.felix.loginapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("auth/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.addUser(userRegisterRequest);
    }

    @PostMapping("auth/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(),
                        userLoginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            UserLoginResponse response = new UserLoginResponse();
            String accessToken = jwtUtil.generateToken(userLoginRequest.getUsername());
            response.setAccessToken(accessToken);
            return response;

        } else {
            throw new UsernameNotFoundException("Invalid credentials.");
        }

    }

    @GetMapping("user")
    public String dashboard() {
        return "Hello user";
    }
}
