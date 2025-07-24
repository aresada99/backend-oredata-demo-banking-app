package com.oredata.banking_demo.models.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionResponse {
    private String fromAccountNumber;
    private String toAccountNumber;
    private UUID fromAccountId;
    private UUID toAccountId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime transactionDate;

}
