package com.example.bank.mappers;

import org.mapstruct.Mapper;

import com.example.bank.query.dto.AccountOperationResponseDTO;
import com.example.bank.query.dto.BankAccountResponseDTO;
import com.example.bank.query.entities.Account;
import com.example.bank.query.entities.AccountOperation;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    BankAccountResponseDTO bankAccountToBankAccountDTO(Account bankAccount);
    AccountOperationResponseDTO accountOperationToAccountOperationDTO(AccountOperation accountOperation);
}