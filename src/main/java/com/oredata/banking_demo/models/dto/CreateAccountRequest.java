package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {

    @NotBlank
    @Schema(description = "Hesap adı (unique olmalı)", example = "Main Account")
    private String name;

    @NotNull
    @Schema(description = "Başlangıç bakiyesi", example = "1000.00")
    private BigDecimal balance;

}
