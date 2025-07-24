package com.oredata.banking_demo.repository;

import com.oredata.banking_demo.models.Account;
import com.oredata.banking_demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByFromIdOrToId(UUID fromId, UUID toId);


}
