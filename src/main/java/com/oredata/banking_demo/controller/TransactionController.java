package com.oredata.banking_demo.controller;

import com.oredata.banking_demo.models.ApiResponse;
import com.oredata.banking_demo.models.dto.TransactionResponse;
import com.oredata.banking_demo.models.dto.TransferRequest;
import com.oredata.banking_demo.security.CustomUserDetails;
import com.oredata.banking_demo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction Management", description = "Transaction operations")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    @Operation(
            summary = "Transfers Money",
            description = "Transfers money in between 2 accounts for the authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<TransactionResponse>> transfer(@Valid @RequestBody TransferRequest request,
                                                Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();

        TransactionResponse response = transactionService.transfer(userId,request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Transfer successfully", response));
    }

    @GetMapping("/account/{id}")
    @Operation(
            summary = "Transaction History",
            description = "Shows the transaction history of the authenticated user's specific account",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionsOfAccount(@PathVariable("id") UUID accountId,
                                                        Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();

        List<TransactionResponse> transactions = transactionService.getSentTransactionsByAccountId(userId, accountId);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Transaction history successfully", transactions));
    }
}
