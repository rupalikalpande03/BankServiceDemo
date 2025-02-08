package com.bankService.service;

import java.util.List;

import com.bankService.dto.AccountDto;
import com.bankService.enception.InvalidAccountException;

public interface AccountService {
	
	public  AccountDto createAccount(AccountDto accountDto);
	
	public List<AccountDto> showAccountDetails();
	
 public AccountDto getAccountById(Long id) throws InvalidAccountException;
 
 public AccountDto deposite(Long accountId, double amount) throws InvalidAccountException ; 

 public AccountDto withdraw(Long id, double amount) throws InvalidAccountException;
 
 void deleteAccount(Long id) throws InvalidAccountException;
 
 void transfer(Long fromAccountId,Long toAccountId, double amount) throws InvalidAccountException;
 
 AccountDto updateAccountDetails(Long accountId,AccountDto accountDto) throws InvalidAccountException;
 
 double getBalance(Long accountId) throws InvalidAccountException;
 
 
 
 
 
}
