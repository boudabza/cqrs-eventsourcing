package com.example.bank.coreapi.event;

import java.math.BigDecimal;

public class AccountDebitedEvent extends BaseEvent<String> {

	private final BigDecimal amount;
	private final String currency;

	public AccountDebitedEvent(String id, BigDecimal amount, String currency) {
		super(id);
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}
}
