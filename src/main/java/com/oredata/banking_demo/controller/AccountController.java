package com.oredata.banking_demo.controller;


import com.oredata.banking_demo.models.ApiResponse;
import com.oredata.banking_demo.models.dto.CreateAccountRequest;
import com.oredata.banking_demo.models.dto.AccountResponse;
import com.oredata.banking_demo.models.dto.UpdateAccountRequest;
import com.oredata.banking_demo.security.CustomUserDetails;
import com.oredata.banking_demo.service.AccountService;
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
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Management", description = "Operations related to bank accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(
            summary = "Create new account",
            description = "Creates a new bank account for the authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<AccountResponse>> createAccount(@Valid @RequestBody CreateAccountRequest request,
                                                     Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();

        AccountResponse response = accountService.createAccount(userId, request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED.value(),true, "Account created successfully", response));
    }

    @PostMapping("/search")
    @Operation(
            summary = "Search accounts",
            description = "Search all accounts for the authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<List<AccountResponse>>> searchAccounts(
            @RequestParam(required = false, defaultValue = "") String number,
            @RequestParam(required = false, defaultValue = "") String name,
            Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();

        List<AccountResponse> accounts = accountService.searchAccounts(userId, number, name);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Account searched successfully", accounts));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update account",
            description = "Updates account for the authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<AccountResponse>> updateAccount(@PathVariable UUID id,
                                                         @Valid @RequestBody UpdateAccountRequest request,
                                                         Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();
        AccountResponse updated = accountService.updateAccount(userId, id, request);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Account updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete account",
            description = "Deletes account for the authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<Object>> deleteAccount(@PathVariable UUID id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();
        accountService.deleteAccount(userId, id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Account deleted successfully", null));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get account details",
            description = "Get account details for the authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountDetails(@PathVariable UUID id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UUID userId = userDetails.getUserId();
        AccountResponse response = accountService.getAccountDetails(userId, id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), true, "Account details found successfully", response));
    }

}
