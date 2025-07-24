package com.oredata.banking_demo.service;

import com.oredata.banking_demo.exception.NotFoundException;
import com.oredata.banking_demo.models.Account;
import com.oredata.banking_demo.models.Transaction;
import com.oredata.banking_demo.models.dto.TransactionResponse;
import com.oredata.banking_demo.models.dto.TransferRequest;
import com.oredata.banking_demo.models.enums.TransactionStatus;
import com.oredata.banking_demo.repository.AccountRepository;
import com.oredata.banking_demo.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionResponse transfer(UUID userId, TransferRequest request) {
        if(request.getSendingAccountNumber().equals(request.getReceivingAccountNumber())){
            throw new RuntimeException("Cannot transfer between same accounts");
        }

        Account sendingAccount = accountRepository.findByNumberAndUserId(request.getSendingAccountNumber(), userId).orElseThrow(() -> new NotFoundException("Sending Account not found or user not own this account"));

        Account receivingAccount = accountRepository.findByNumber(request.getReceivingAccountNumber()).orElseThrow(() -> new NotFoundException("Receiving Account not found"));

        // Check sending account balance
        if(sendingAccount.getBalance().compareTo(request.getAmount()) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        sendingAccount.setBalance(sendingAccount.getBalance().subtract(request.getAmount()));
        receivingAccount.setBalance(receivingAccount.getBalance().add(request.getAmount()));

        Transaction transaction = new Transaction();
        transaction.setFrom(sendingAccount);
        transaction.setTo(receivingAccount);
        transaction.setAmount(request.getAmount());
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);
        accountRepository.save(sendingAccount);
        accountRepository.save(receivingAccount);

        return toTransactionResponse(transaction);
    }

    public List<TransactionResponse> getSentTransactionsByAccountId(UUID userId, UUID accountId) {
        boolean hasAccess = accountRepository.existsByIdAndUserId(accountId, userId);
        if (!hasAccess) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This account does not belong to the authenticated user.");
        }

        List<Transaction> transactions = transactionRepository
                .findByFromIdOrToId(accountId, accountId);

        return transactions.stream()
                .map(this::toTransactionResponse)
                .collect(Collectors.toList());
    }




    public TransactionResponse toTransactionResponse(Transaction transaction) {
        TransactionResponse dto = new TransactionResponse();
        dto.setFromAccountNumber(transaction.getFrom().getNumber());
        dto.setToAccountNumber(transaction.getTo().getNumber());
        dto.setFromAccountId(transaction.getFrom().getId());
        dto.setToAccountId(transaction.getTo().getId());
        dto.setAmount(transaction.getAmount());
        dto.setStatus(transaction.getStatus().name());
        dto.setTransactionDate(transaction.getTransactionDate());
        return dto;
    }
}

