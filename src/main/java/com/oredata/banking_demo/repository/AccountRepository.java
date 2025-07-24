package com.oredata.banking_demo.repository;

import com.oredata.banking_demo.models.Account;
import com.oredata.banking_demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByUserId(UUID userId);
    Optional<Account> findByIdAndUser(UUID id, User user);
    Optional<Account> findByIdAndUserId(UUID id, UUID userId);
    Optional<Account> findByNumberAndUserId(String number, UUID userId);
    Optional<Account> findByNumber(String number);
    boolean existsByIdAndUserId(UUID id, UUID userId);

    @Query("SELECT a FROM Account a WHERE a.user.id = :userId " +
            "AND (:number IS NULL OR LOWER(a.number) LIKE LOWER(CONCAT('%', :number, '%'))) " +
            "AND (:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<Account> searchAccounts(@org.springframework.lang.NonNull UUID userId,
                                 @org.springframework.lang.Nullable String number,
                                 @org.springframework.lang.Nullable String name);

}
