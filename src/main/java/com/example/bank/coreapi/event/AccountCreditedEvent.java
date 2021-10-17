package com.example.bank.coreapi.event;

import java.math.BigDecimal;

public class AccountCreditedEvent  extends BaseEvent<String>{


	private BigDecimal amount;
	private String currency;
	
	public AccountCreditedEvent (String id, BigDecimal initialBalance, String currency) {
		super(id);
		this.amount = initialBalance;
		this.currency = currency;
	}
	public BigDecimal getInitialBalance() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	
	
}
