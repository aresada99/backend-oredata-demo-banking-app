package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {

    @NotBlank
    @Schema(description = "Account Name (unique)", example = "Main Account")
    private String name;

    @NotNull
    @Schema(description = "Starting Balance", example = "1000.00")
    private BigDecimal balance;

}
