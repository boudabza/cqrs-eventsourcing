package com.example.bank.command.services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.command.dto.CreateAccountRequestDto;
import com.example.bank.command.dto.CreditAccountRequestDTO;
import com.example.bank.command.dto.DebitAccountRequestDTO;
import com.example.bank.coreapi.command.CreatAccountCommand;
import com.example.bank.coreapi.command.CreditAccountCommand;
import com.example.bank.coreapi.command.DebitAccountCommand;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

	@Autowired
	private CommandGateway commandGateway;

	@Override
	public CompletableFuture<String> createAccount(CreateAccountRequestDto accountRequestDTO) {
		 return commandGateway.send(new CreatAccountCommand(UUID.randomUUID().toString(), accountRequestDTO.getInitialBalance(), accountRequestDTO.getCurrency()));
	}

	@Override
	public CompletableFuture<String> debitAccount(DebitAccountRequestDTO debitAccountRequestDTO) {
		return commandGateway.send(new DebitAccountCommand(debitAccountRequestDTO.getAccountId(),
				debitAccountRequestDTO.getAmount(), debitAccountRequestDTO.getCurrency()));
	}

	@Override
	public CompletableFuture<String> creditAccount(CreditAccountRequestDTO creditAccountRequestDTO) {
		return commandGateway.send(new CreditAccountCommand(creditAccountRequestDTO.getAccountId(),
				creditAccountRequestDTO.getAmount(), creditAccountRequestDTO.getCurrency()));
	}

}
