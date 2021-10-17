package com.example.bank.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.query.entities.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
