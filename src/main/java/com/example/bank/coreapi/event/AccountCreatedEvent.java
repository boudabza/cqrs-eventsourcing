package com.example.bank.coreapi.event;

import java.math.BigDecimal;

import com.example.bank.enums.AccountStatus;

public class AccountCreatedEvent extends BaseEvent<String> {
	private final BigDecimal balance;
	private final String currency;
	private final AccountStatus status;

	public AccountCreatedEvent(String id, BigDecimal balance, String currency, AccountStatus status) {
		super(id);
		this.balance = balance;
		this.currency = currency;
		this.status = status;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getCurrency() {
		return currency;
	}

	public AccountStatus getStatus() {
		return status;
	}

}
