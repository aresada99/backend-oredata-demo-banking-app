package com.oredata.banking_demo.service;


import com.oredata.banking_demo.models.User;
import com.oredata.banking_demo.models.dto.LoginResponse;
import com.oredata.banking_demo.models.dto.UserLoginRequest;
import com.oredata.banking_demo.models.dto.UserRegisterRequest;
import com.oredata.banking_demo.repository.UserRepository;
import com.oredata.banking_demo.security.CustomUserDetails;
import com.oredata.banking_demo.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public User register(UserRegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        userRepository.save(user);
        return user;
    }

    public LoginResponse login(UserLoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var authentication = authenticationManager.authenticate(authenticationToken);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);
        UUID userId = userDetails.getUserId();
        String username = userDetails.getUsername();
        String email = userDetails.getUser().getEmail();

        return new LoginResponse(userId,username,email,token);
    }
}
