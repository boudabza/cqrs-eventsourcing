package com.example.bank.command.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4061132367239841761L;

	public InsufficientBalanceException(String message) {
        super(message);
    }
}
