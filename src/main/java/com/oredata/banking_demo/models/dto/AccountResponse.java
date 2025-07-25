package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountResponse {
    @Schema(description = "Account UUID", example = "1e6e4ad2-23e2-4a97-b8c6-38f45c210fad")
    private UUID id;

    @Schema(description = "Account Name", example = "Main Account")
    private String name;

    @Schema(description = "Unique Account Number", example = "ACC-000123")
    private String number;

    @Schema(description = "Account Balance", example = "1500.00")
    private BigDecimal balance;
}
