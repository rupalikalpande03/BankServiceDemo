package com.bankService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.bankService.dto.AccountDto;
import com.bankService.enception.InvalidAccountException;
import com.bankService.entity.Account;
import com.bankService.mapper.AccountMapper;
import com.bankService.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	public AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {

		Account account = AccountMapper.accountDtoToAccount(accountDto);

		Account savedAccount = accountRepository.save(account);
		AccountDto adto = AccountMapper.accountToAccountDto(savedAccount);
		return adto;
	}

	@Override
	public List<AccountDto> showAccountDetails() {
		List<Account> allAccount = accountRepository.findAll();
		List<AccountDto> list = allAccount.stream().map(entity -> AccountMapper.accountToAccountDto(entity))
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public AccountDto getAccountById(Long id) throws InvalidAccountException {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new InvalidAccountException("Invalid Account"));
		return AccountMapper.accountToAccountDto(account);
	}

	public AccountDto deposite(Long accountId, double amount) throws InvalidAccountException {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new InvalidAccountException("Account Not Found"));

		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savdAccount = accountRepository.save(account);

		return AccountMapper.accountToAccountDto(savdAccount);

	}

	@Override
	public AccountDto withdraw(Long id, double amount) throws InvalidAccountException {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new InvalidAccountException("Account does not exist"));

		if (account.getBalance() < amount) {
			throw new InvalidAccountException("Insufficient Amount");
		}
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);

		return AccountMapper.accountToAccountDto(savedAccount);
	}

	@Override
	public void deleteAccount(Long id) throws InvalidAccountException {
		accountRepository.findById(id).orElseThrow(() -> new InvalidAccountException("Account does not exist"));

		accountRepository.deleteById(id);
	}

	@Override
	public void transfer(Long fromAccountId, Long toAccountId, double amount) throws InvalidAccountException {
		withdraw(toAccountId, amount);
		deposite(toAccountId, amount);

	}

	@Override
	public AccountDto updateAccountDetails(Long accountId, AccountDto accountDto) throws InvalidAccountException {

		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new InvalidAccountException("Account not found"));

		account.setAccountHolderName(accountDto.getAccountHolderName());
		account.setBalance(accountDto.getBalance());
		Account updatedAccount = accountRepository.save(account);
		return AccountMapper.accountToAccountDto(updatedAccount);
	}

	@Override
	public double getBalance(Long accountId) throws InvalidAccountException {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new InvalidAccountException("Account not found"));

		return account.getBalance();
	}

}
