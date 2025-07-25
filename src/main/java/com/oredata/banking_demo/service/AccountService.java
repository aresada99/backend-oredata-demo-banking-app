package com.oredata.banking_demo.service;

import com.oredata.banking_demo.exception.NotFoundException;
import com.oredata.banking_demo.models.Account;
import com.oredata.banking_demo.models.User;
import com.oredata.banking_demo.models.dto.CreateAccountRequest;
import com.oredata.banking_demo.models.dto.AccountResponse;
import com.oredata.banking_demo.models.dto.UpdateAccountRequest;
import com.oredata.banking_demo.repository.AccountRepository;
import com.oredata.banking_demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountResponse createAccount(UUID userId, CreateAccountRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        account.setNumber(generateAccountNumber());

        account = accountRepository.save(account);
        return convertToResponse(account);
    }

    public List<AccountResponse> searchAccounts(UUID userId, String number, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        number = (number != null && !number.isBlank()) ? number : null;
        name = (name != null && !name.isBlank()) ? name : null;

        List<Account> accounts = accountRepository.searchAccounts(user.getId(), number, name);



        return accounts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public AccountResponse updateAccount(UUID userId, UUID accountId, UpdateAccountRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Account account = accountRepository.findByIdAndUser(accountId, user)
                .orElseThrow(() -> new NotFoundException("Account not found or not owned by user"));

        account.setName(request.getName());

        account = accountRepository.save(account);
        return convertToResponse(account);
    }

    public void deleteAccount(UUID userId, UUID accountId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Account account = accountRepository.findByIdAndUser(accountId, user)
                .orElseThrow(() -> new NotFoundException("Account not found or not owned by user"));

        accountRepository.delete(account);
    }

    public AccountResponse getAccountDetails(UUID userId, UUID accountId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Account account = accountRepository.findByIdAndUser(accountId, user)
                .orElseThrow(() -> new NotFoundException("Account not found or not owned by user"));

        return convertToResponse(account);
    }

    private AccountResponse convertToResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getNumber(),
                account.getBalance()
        );
    }

    private String generateAccountNumber() {
        long count = accountRepository.count() + 1;
        return "ACC-" + String.format("%06d", count);
    }

}
