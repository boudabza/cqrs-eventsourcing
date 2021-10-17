package com.example.bank.command.services;

import java.util.concurrent.CompletableFuture;

import com.example.bank.command.dto.CreateAccountRequestDto;
import com.example.bank.command.dto.CreditAccountRequestDTO;
import com.example.bank.command.dto.DebitAccountRequestDTO;

public interface AccountCommandService {

	CompletableFuture<String> createAccount(CreateAccountRequestDto accountRequestDTO);
    CompletableFuture<String> debitAccount(DebitAccountRequestDTO debitAccountRequestDTO);
    CompletableFuture<String> creditAccount(CreditAccountRequestDTO creditAccountRequestDTO);
}
