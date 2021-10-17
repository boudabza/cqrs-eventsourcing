package com.example.bank.coreapi.command;

import java.math.BigDecimal;

public class CreditAccountCommand extends BaseCommand<String>{

	private BigDecimal amount;
	private String currency;
	
	
	public CreditAccountCommand(String id, BigDecimal amount, String currency) {
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
