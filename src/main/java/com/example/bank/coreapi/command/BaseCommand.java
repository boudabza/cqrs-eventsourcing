package com.example.bank.coreapi.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

	// Corespond à l'identifiant de l'agregat
	// Avec axon c'est obligatoir de specifier l'attribut qui represente l'id de l'agregat
	// sur execute la commande dans notre cas c'est l'id du compte
	@TargetAggregateIdentifier
	private T id;
	
	public BaseCommand(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}
	
	
}
