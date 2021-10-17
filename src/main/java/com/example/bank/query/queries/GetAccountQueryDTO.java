package com.example.bank.query.queries;

public class GetAccountQueryDTO {

	private String id;

	public GetAccountQueryDTO() {
	}

	public GetAccountQueryDTO(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
