package com.example.bank.command.dto;

import java.math.BigDecimal;

public class CreateAccountRequestDto {
	private BigDecimal initialBalance;
	private String currency;

	public CreateAccountRequestDto(BigDecimal initialBalance, String currency) {
		super();
		this.initialBalance = initialBalance;
		this.currency = currency;
	}

	public CreateAccountRequestDto() {

	}

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
