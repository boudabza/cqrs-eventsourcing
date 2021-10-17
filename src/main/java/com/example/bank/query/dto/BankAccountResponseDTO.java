package com.example.bank.query.dto;

import java.math.BigDecimal;

import com.example.bank.enums.AccountStatus;

public class BankAccountResponseDTO {
	private String id;
	private BigDecimal balance;
	private AccountStatus status;

	public BankAccountResponseDTO() {

	}

	public BankAccountResponseDTO(String id, BigDecimal balance, AccountStatus status) {
		super();
		this.id = id;
		this.balance = balance;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

}
