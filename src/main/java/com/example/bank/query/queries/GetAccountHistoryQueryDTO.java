package com.example.bank.query.queries;

public class GetAccountHistoryQueryDTO {
	private String accountId;

	public GetAccountHistoryQueryDTO() {

	}

	public GetAccountHistoryQueryDTO(String accountId) {
		super();
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
