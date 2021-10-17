package com.example.bank.coreapi.command;

import java.math.BigDecimal;

public class CreatAccountCommand extends BaseCommand<String>{


	private BigDecimal initialBalance;
	private String currency;
	
	public CreatAccountCommand(String id, BigDecimal initialBalance, String currency) {
		super(id);
		this.initialBalance = initialBalance;
		this.currency = currency;
	}
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	public String getCurrency() {
		return currency;
	}
	
	
}
