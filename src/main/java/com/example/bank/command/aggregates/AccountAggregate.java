package com.example.bank.command.aggregates;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bank.command.exceptions.InsufficientBalanceException;
import com.example.bank.coreapi.command.CreatAccountCommand;
import com.example.bank.coreapi.command.CreditAccountCommand;
import com.example.bank.coreapi.command.DebitAccountCommand;
import com.example.bank.coreapi.event.AccountActivatedEvent;
import com.example.bank.coreapi.event.AccountCreatedEvent;
import com.example.bank.coreapi.event.AccountCreditedEvent;
import com.example.bank.coreapi.event.AccountDebitedEvent;
import com.example.bank.enums.AccountStatus;

//Contient les handler (le domaine sur lequelle s'execute la commande)
@Aggregate
public class AccountAggregate {

	// l'attribue qui represente l'id de l'aggrega
	@AggregateIdentifier
	private String accountId;
	private BigDecimal balance;
	private String currency;
	private String status;

	Logger log = LoggerFactory.getLogger(AccountAggregate.class);

	public AccountAggregate() {
	}

	// Dés il ya une commande create account va le prendre
	@CommandHandler
	public AccountAggregate(CreatAccountCommand createAccountCommand) {
		/* logic metier avant de l'accepter */
		log.info("CreateAccountCommand Received");
		AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(),
				createAccountCommand.getInitialBalance(), createAccountCommand.getCurrency(), AccountStatus.CREATED));
	}

	// Mettre a jour l'etat de l'application
	@EventSourcingHandler
	public void on(AccountCreatedEvent accountCreatedEvent) {
		log.info("AccountCreatedEvent Occured");
		this.accountId = accountCreatedEvent.getId();
		this.balance = accountCreatedEvent.getBalance();
		this.currency = accountCreatedEvent.getCurrency();
		this.status = String.valueOf(accountCreatedEvent.getStatus());
		// dispatch l'evenement d'activation du compte
		AggregateLifecycle.apply(new AccountActivatedEvent(this.accountId, AccountStatus.ACTIVATED));
	}

	@EventSourcingHandler
	public void on(AccountActivatedEvent accountActivatedEvent) {
		log.info("AccountActivatedEvent Occured");
		this.status = String.valueOf(accountActivatedEvent.getStatus());
	}

	@CommandHandler
	public void on(DebitAccountCommand debitAccountCommand) {
		if ((this.balance.doubleValue() > 0)
				&& (this.balance.subtract(debitAccountCommand.getAmount()).doubleValue() < 0)) {
			throw new InsufficientBalanceException("Insufficient Balance=>" + this.balance.doubleValue());
		} else
			AggregateLifecycle.apply(new AccountDebitedEvent(debitAccountCommand.getId(),
					debitAccountCommand.getAmount(), debitAccountCommand.getCurrency()));
	}

	@EventSourcingHandler
	public void on(AccountDebitedEvent accountDebitedEvent) {
		log.info("AccountDebitedEvent Occured");
		this.balance = this.balance.subtract(accountDebitedEvent.getAmount());
		System.out.println(this.balance);
	}

//    @EventSourcingHandler
//    public void on(AccountHeldEvent accountHeldEvent){
//        this.status=String.valueOf(accountHeldEvent.getStatus());
//    }
	@CommandHandler
	public void on(CreditAccountCommand creditAccountCommand) {
		AggregateLifecycle.apply(new AccountCreditedEvent(creditAccountCommand.getId(),
				creditAccountCommand.getAmount(), creditAccountCommand.getCurrency()));
	}

	@EventSourcingHandler
	public void on(AccountCreditedEvent accountCreditedEvent) {
		this.balance = this.balance.add(accountCreditedEvent.getInitialBalance());
		log.info("==================");
		log.info(this.balance.toString());
		log.info("==================");
	}
}
