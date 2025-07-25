package com.oredata.banking_demo.controller;

import com.oredata.banking_demo.models.ApiResponse;
import com.oredata.banking_demo.models.User;
import com.oredata.banking_demo.models.dto.LoginResponse;
import com.oredata.banking_demo.models.dto.UserLoginRequest;
import com.oredata.banking_demo.models.dto.UserRegisterRequest;
import com.oredata.banking_demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "User operations")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(
            summary = "Registers user",
            description = "Registers user"
    )
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody UserRegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(), true, "User registered successfully", user));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Logins user",
            description = "Logins user"
    )
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody UserLoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Login successfully", response));
    }
}
