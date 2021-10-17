package com.example.bank.query.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.bank.enums.OperationType;

@Entity
public class AccountOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date operationDate;
	private BigDecimal amount;
	@Enumerated(EnumType.STRING)
	private OperationType type;
	@ManyToOne
	private Account bankAccount;

	public AccountOperation() {

	}

	public AccountOperation(Long id, Date operationDate, BigDecimal amount, OperationType type, Account bankAccount) {
		super();
		this.id = id;
		this.operationDate = operationDate;
		this.amount = amount;
		this.type = type;
		this.bankAccount = bankAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public Account getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Account bankAccount) {
		this.bankAccount = bankAccount;
	}
}
