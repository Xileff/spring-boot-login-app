package com.felix.loginapp.services;

import com.felix.loginapp.entities.User;
import com.felix.loginapp.entities.dto.requests.UserRegisterRequest;
import com.felix.loginapp.entities.dto.responses.UserRegisterResponse;
import com.felix.loginapp.repositories.UserRepository;
import com.felix.loginapp.utils.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
    }

    public UserRegisterResponse addUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setName(userRegisterRequest.getName());
        user.setUsername(userRegisterRequest.getUsername());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setIsActive(true);
        userRepository.save(user);

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setMessage("Registered successfully.");
        return userRegisterResponse;
    }
}
