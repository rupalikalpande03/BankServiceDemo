package com.bankService.mapper;

import com.bankService.dto.AccountDto;
import com.bankService.entity.Account;

public class AccountMapper {

	public static AccountDto accountToAccountDto(Account account) {

		AccountDto accountdto = new AccountDto();
		accountdto.setId(account.getId());
		accountdto.setAccountHolderName(account.getAccountHolderName());
		accountdto.setBalance(account.getBalance());

		return accountdto;

	}
	
	
	
	public static Account accountDtoToAccount(AccountDto accountdto) {
	
		Account account = new Account();
		account.setId(accountdto.getId());
		account.setAccountHolderName(accountdto.getAccountHolderName());
		account.setBalance(accountdto.getBalance());
		
		return account;
		
	}

}
