package com.example.bank.query.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.coreapi.event.AccountActivatedEvent;
import com.example.bank.coreapi.event.AccountCreatedEvent;
import com.example.bank.coreapi.event.AccountCreditedEvent;
import com.example.bank.coreapi.event.AccountDebitedEvent;
import com.example.bank.enums.AccountStatus;
import com.example.bank.enums.OperationType;
import com.example.bank.mappers.BankAccountMapper;
import com.example.bank.query.dto.AccountHistoryDto;
import com.example.bank.query.dto.AccountOperationResponseDTO;
import com.example.bank.query.dto.BankAccountResponseDTO;
import com.example.bank.query.entities.Account;
import com.example.bank.query.entities.AccountOperation;
import com.example.bank.query.queries.GetAccountHistoryQueryDTO;
import com.example.bank.query.queries.GetAccountOperationsQueryDTO;
import com.example.bank.query.queries.GetAccountQueryDTO;
import com.example.bank.query.queries.GetAllAccountsRequestDTO;
import com.example.bank.query.repository.AccountOperationRepository;
import com.example.bank.query.repository.AccountRepository;

@Service
public class AccountQueryService {

	private final AccountRepository accountRepository;
	private final AccountOperationRepository accountOperationRepository;
	@Autowired
	private BankAccountMapper bankAccountMapper;
    private final QueryUpdateEmitter queryUpdateEmitter;
    

    public AccountQueryService(AccountRepository accountRepository, AccountOperationRepository accountOperationRepository, QueryUpdateEmitter queryUpdateEmitter) {
        this.accountRepository = accountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }
	// !!! Difference entre EventHandler et EventSourcineHandler
	@EventHandler
	@Transactional
	public void on(AccountCreatedEvent accountCreatedEvent) {
		Account bankAccount = new Account();
		bankAccount.setId(accountCreatedEvent.getId());
		bankAccount.setBalance(accountCreatedEvent.getBalance());
		bankAccount.setStatus(AccountStatus.CREATED);
		bankAccount.setCurrency(accountCreatedEvent.getCurrency());
		accountRepository.save(bankAccount);
	}

	@EventHandler
	public void on(AccountActivatedEvent accountActivatedEvent) {
		Account bankAccount = accountRepository.findById(accountActivatedEvent.getId()).get();
		bankAccount.setStatus(AccountStatus.ACTIVATED);
		accountRepository.save(bankAccount);
	}

	@EventHandler
	@Transactional
	public void on(AccountDebitedEvent accountDebitedEvent) {
		Account bankAccount = accountRepository.findById(accountDebitedEvent.getId()).get();
		bankAccount.setBalance(bankAccount.getBalance().subtract(accountDebitedEvent.getAmount()));
		Account savedAccount = accountRepository.save(bankAccount);
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setOperationDate(new Date());
		accountOperation.setAmount(accountDebitedEvent.getAmount());
		accountOperation.setBankAccount(savedAccount);
		accountOperation.setType(OperationType.DEBIT);
		accountOperationRepository.save(accountOperation);
		/// Utiliser pour envoyer evenent en temps réel , ( Emettre un evenement pour l'objet qui est fait une suscription
		/// Voir dans le controller
		
		//Tester if l'account correspond au id d'account de l'evenement
		queryUpdateEmitter.emit(m -> ((GetAccountQueryDTO) m.getPayload()).getId().equals(accountDebitedEvent.getId()),
				bankAccountMapper.bankAccountToBankAccountDTO(bankAccount));
	}

	@EventHandler
	public void on(AccountCreditedEvent accountCreditedEvent) {
		Account bankAccount = accountRepository.findById(accountCreditedEvent.getId()).get();
		bankAccount.setBalance(bankAccount.getBalance().add(accountCreditedEvent.getInitialBalance()));
		Account savedAccount = accountRepository.save(bankAccount);
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setOperationDate(new Date());
		accountOperation.setAmount(accountCreditedEvent.getInitialBalance());
		accountOperation.setBankAccount(savedAccount);
		accountOperation.setType(OperationType.CREDIT);
		accountOperationRepository.save(accountOperation);
		queryUpdateEmitter.emit(m -> ((GetAccountQueryDTO) m.getPayload()).getId().equals(accountCreditedEvent.getId()),
				bankAccountMapper.bankAccountToBankAccountDTO(bankAccount));
	}

	@QueryHandler
	public BankAccountResponseDTO on(GetAccountQueryDTO accountQuery) {
		Account bankAccount = accountRepository.findById(accountQuery.getId()).get();
		return bankAccountMapper.bankAccountToBankAccountDTO(bankAccount);
	}

	@QueryHandler
	public List<BankAccountResponseDTO> on(GetAllAccountsRequestDTO accountsRequest) {
		List<Account> bankAccountList = accountRepository.findAll();
		return bankAccountList.stream().map((acc -> bankAccountMapper.bankAccountToBankAccountDTO(acc)))
				.collect(Collectors.toList());
	}
    @QueryHandler
    public List<AccountOperationResponseDTO> on(GetAccountOperationsQueryDTO getAccountOperationsQueryDTO) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(getAccountOperationsQueryDTO.getAccountId());
        return accountOperations.stream().map(op->bankAccountMapper.accountOperationToAccountOperationDTO(op)).collect(Collectors.toList());
    }
    @QueryHandler
    public AccountHistoryDto on(GetAccountHistoryQueryDTO getAccountHistoryQueryDTO) {
    	Account account = accountRepository.findById(getAccountHistoryQueryDTO.getAccountId()).get();
    	BankAccountResponseDTO accountDto = bankAccountMapper.bankAccountToBankAccountDTO(account);
    	List<AccountOperation> operations = accountOperationRepository.findByBankAccountId(getAccountHistoryQueryDTO.getAccountId());
        List<AccountOperationResponseDTO> accountOperations = operations.stream().map(op->bankAccountMapper.accountOperationToAccountOperationDTO(op)).collect(Collectors.toList());
        return new AccountHistoryDto(accountDto,accountOperations);
    }
}
