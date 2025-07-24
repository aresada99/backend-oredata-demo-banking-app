package com.oredata.banking_demo.models.dto;

import io.swagger.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull(message = "Gönderen hesap numarası boş olamaz")
    @Schema(description = "Para gönderen hesap Id", example = "Main Account")
    private String sendingAccountNumber;
    @NotNull(message = "Alıcı hesap numarası boş olamaz")
    @Schema(description = "Para'nın gönderildiği hesap Id", example = "Dollar Account")
    private String receivingAccountNumber;
    @NotNull(message = "Tutar boş olamaz")
    @Schema(description = "Gönderilen Para Miktarı", example = "1000")
    private BigDecimal amount;
}
