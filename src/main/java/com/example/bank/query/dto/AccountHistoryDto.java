package com.example.bank.query.dto;

import java.util.List;

public class AccountHistoryDto {

	private BankAccountResponseDTO account;
	List<AccountOperationResponseDTO> operations;

	public AccountHistoryDto(BankAccountResponseDTO account, List<AccountOperationResponseDTO> operations) {
		super();
		this.account = account;
		this.operations = operations;
	}

	public AccountHistoryDto() {
	}

	public BankAccountResponseDTO getAccount() {
		return account;
	}

	public void setAccount(BankAccountResponseDTO account) {
		this.account = account;
	}

	public List<AccountOperationResponseDTO> getOperations() {
		return operations;
	}

	public void setOperations(List<AccountOperationResponseDTO> operations) {
		this.operations = operations;
	}

}
