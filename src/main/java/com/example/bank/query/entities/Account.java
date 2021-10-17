package com.example.bank.query.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.example.bank.enums.AccountStatus;

@Entity
public class Account {
	
	@Id
	private String id;
	private BigDecimal balance;
	private String currency;
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	public Account() {
	}

	public Account(String id, BigDecimal balance, String currency, AccountStatus status) {
		super();
		this.id = id;
		this.balance = balance;
		this.currency = currency;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

}
