package com.example.bank.command.controllers;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.command.dto.CreateAccountRequestDto;
import com.example.bank.command.dto.CreditAccountRequestDTO;
import com.example.bank.command.dto.DebitAccountRequestDTO;
import com.example.bank.command.exceptions.InsufficientBalanceException;
import com.example.bank.command.services.AccountCommandService;

@RestController
@RequestMapping(path = "/commands")
public class AccounCommandRestApi {

	@Autowired
	private  AccountCommandService accountCommandService;

	@Autowired
	EventStore eventStore;

	@PostMapping(path = "/accounts/create")
	public CompletableFuture<String> newAccount(@RequestBody CreateAccountRequestDto request) {
		return accountCommandService.createAccount(request);
	}

	@PutMapping(path = "/accounts/debit")
	public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debitAccountRequestDTO) {
		return accountCommandService.debitAccount(debitAccountRequestDTO);
	}

	@PutMapping(path = "/accounts/credit")
	public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO creditAccountRequestDTO) {
		return accountCommandService.creditAccount(creditAccountRequestDTO);
	}

	@GetMapping(path = "/events/{accountId}")
	public Stream<?> accountEvents(@PathVariable String accountId) {
		return eventStore.readEvents(accountId).asStream();
	}
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> exceptionHandler(InsufficientBalanceException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
