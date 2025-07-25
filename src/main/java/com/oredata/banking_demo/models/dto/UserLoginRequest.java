package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {

    @NotBlank(message = "Username is mandatory")
    @Schema(description = "Username", example = "exampleusername")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Schema(description = "Password", example = "password")
    private String password;

}
