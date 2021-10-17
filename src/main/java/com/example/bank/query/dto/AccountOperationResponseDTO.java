package com.example.bank.query.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.example.bank.enums.OperationType;

public class AccountOperationResponseDTO {

	private Long id;
	private Date operationDate;
	private BigDecimal amount;
	private OperationType type;

	public AccountOperationResponseDTO() {

	}

	public AccountOperationResponseDTO(Long id, Date operationDate, BigDecimal amount, OperationType type) {
		super();
		this.id = id;
		this.operationDate = operationDate;
		this.amount = amount;
		this.type = type;
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

}
