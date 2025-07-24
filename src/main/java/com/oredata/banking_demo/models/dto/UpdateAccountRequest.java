package com.oredata.banking_demo.models.dto;

import io.swagger.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAccountRequest {
    @NotBlank
    @Schema(description = "Hesap adı (unique olmalı)", example = "Main Account")
    private String name;
}