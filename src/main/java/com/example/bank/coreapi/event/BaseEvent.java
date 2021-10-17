package com.example.bank.coreapi.event;

public abstract class BaseEvent<T> {

	private T id;

	public BaseEvent(T id) {
		super();
		this.id = id;
	}

	public T getId() {
		return id;
	}

}
