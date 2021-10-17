package com.example.bank.query.queries;
public class GetAccountOperationsQueryDTO {
	private String accountId;

	public GetAccountOperationsQueryDTO() {

	}

	public GetAccountOperationsQueryDTO(String accountId) {
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
