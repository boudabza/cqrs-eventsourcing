package com.example.bank.coreapi.event;

import com.example.bank.enums.AccountStatus;

public class AccountActivatedEvent extends BaseEvent<String> {

	private final AccountStatus status;

	public AccountActivatedEvent(String id, AccountStatus status) {
		super(id);
		this.status = status;
	}

	public AccountStatus getStatus() {
		return status;
	}
	
}
