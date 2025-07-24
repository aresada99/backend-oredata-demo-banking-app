package com.oredata.banking_demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AccountResponse {
    @Schema(description = "Hesap UUID bilgisi", example = "1e6e4ad2-23e2-4a97-b8c6-38f45c210fad")
    private UUID id;

    @Schema(description = "Hesap adı", example = "Main Account")
    private String name;

    @Schema(description = "Sistem tarafından üretilen benzersiz hesap numarası", example = "ACC-000123")
    private String number;

    @Schema(description = "Hesap bakiyesi", example = "1500.75")
    private BigDecimal balance;
}
