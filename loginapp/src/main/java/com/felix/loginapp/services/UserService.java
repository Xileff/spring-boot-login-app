package com.felix.loginapp.services;

import com.felix.loginapp.entities.User;
import com.felix.loginapp.entities.dto.ResponseBody;
import com.felix.loginapp.entities.dto.requests.UserLoginRequest;
import com.felix.loginapp.entities.dto.requests.UserRegisterRequest;
import com.felix.loginapp.entities.dto.responses.UserLoginResponse;
import com.felix.loginapp.entities.dto.responses.UserRegisterResponse;
import com.felix.loginapp.entities.enums.Actions;
import com.felix.loginapp.repositories.UserRepository;
import com.felix.loginapp.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuditTrailService auditTrailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ResponseEntity<ResponseBody> insertUser(UserRegisterRequest userRegisterRequest) {
        try {
            User user = new User();
            user.setName(userRegisterRequest.getName());
            user.setUsername(userRegisterRequest.getUsername());
            user.setEmail(userRegisterRequest.getEmail());
            user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
            user.setIsActive(true);
            userRepository.save(user);
            auditTrailService.insertAuditTrail(user, Actions.CREATE, "/auth/register", "User created account.");

            UserRegisterResponse userRegisterResponse = new UserRegisterResponse(user.getUsername(), "Registered successfully.");
            ResponseBody<UserRegisterResponse> body = new ResponseBody<>("success", userRegisterResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (ResponseStatusException e) {
            ResponseBody<String> body = new ResponseBody<>("failed", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(body);
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
            ResponseBody<String> body = new ResponseBody<>("failed", "Oops, something went wrong!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    public ResponseEntity<ResponseBody> login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            User user = userRepository.findByUsername(userLoginRequest.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

            if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
                String accessToken = jwtUtil.generateToken(userLoginRequest.getUsername());
                auditTrailService.insertAuditTrail(user, Actions.CREATE, "/auth/login", "User logged in.");
                UserLoginResponse userLoginResponse = new UserLoginResponse(accessToken);
                ResponseBody<UserLoginResponse> body = new ResponseBody<>("success", userLoginResponse);
                return ResponseEntity.status(HttpStatus.CREATED).body(body);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
            }
        } catch (ResponseStatusException e) {
            ResponseBody<String> body = new ResponseBody<>("failed", e.getReason());
            return ResponseEntity.status(e.getStatusCode()).body(body);
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
            ResponseBody<String> body = new ResponseBody<>("failed", "Oops, something went wrong!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    public ResponseEntity<ResponseBody> getDashboard() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            String dashboardText = "Welcome, " + username;
            ResponseBody<String> body = new ResponseBody<>("success", dashboardText);
            return ResponseEntity.status(HttpStatus.OK).body(body);
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
            ResponseBody<String> body = new ResponseBody<>("failed", "Oops, something went wrong!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}
