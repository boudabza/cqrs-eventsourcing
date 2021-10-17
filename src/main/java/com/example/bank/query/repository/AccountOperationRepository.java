package com.example.bank.query.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.query.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
	List<AccountOperation> findByBankAccountId(String accountId);
}
