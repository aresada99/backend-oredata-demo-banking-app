package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "Username is mandatory")
    @Schema(description = "Username", example = "exampleusername")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Schema(description = "Password", example = "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")
    private String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Schema(description = "Email", example = "example@mail.com")
    private String email;
}
