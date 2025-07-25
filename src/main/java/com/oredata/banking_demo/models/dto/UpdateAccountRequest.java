package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAccountRequest {
    @NotBlank
    @Schema(description = "Account Name (unique)", example = "Main Account")
    private String name;
}