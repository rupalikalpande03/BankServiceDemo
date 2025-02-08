package com.bankService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankService.dto.AccountDto;
import com.bankService.enception.InvalidAccountException;
import com.bankService.service.AccountService;

@RestController
@RequestMapping("/api/accounts")


public class AccountController {

	@Autowired
	public AccountService accountservice;

	@PostMapping
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
		AccountDto addaccount = accountservice.createAccount(accountDto);

		return new ResponseEntity<>(addaccount, HttpStatus.CREATED);

	}

	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>> showAllAccount() {

		List<AccountDto> showaccounts = accountservice.showAccountDetails();
		return new ResponseEntity<List<AccountDto>>(showaccounts, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) throws InvalidAccountException {

		AccountDto accountDto = accountservice.getAccountById(id);

		return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);

	}
	
	@PutMapping("/{id}/deposite")
	public ResponseEntity<AccountDto> deposite( @PathVariable Long id, @RequestBody Map<String, Double> request) throws InvalidAccountException{
		
	Double amount = request.get("amount");
  AccountDto accountDto=   accountservice.deposite(id, amount);
     return ResponseEntity.ok(accountDto);
	
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request) throws InvalidAccountException{
		
		double amount=request.get("amount");
	AccountDto accountDto=	accountservice.withdraw(id, amount);
	return ResponseEntity.ok(accountDto);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) throws InvalidAccountException{
	 accountservice.deleteAccount(id);
	 return ResponseEntity.ok("Account is deleted successfully");
}
	
	@PutMapping("/transfer/{fromId}/{toId}/{amount}")
	public ResponseEntity<String> transfer(@PathVariable Long fromId,@PathVariable Long toId,@PathVariable double amount) throws InvalidAccountException{
	
		accountservice.transfer(fromId, toId, amount);
		
		return ResponseEntity.ok("Transfer successful");

}
	@PutMapping("/{accountId}")
	public ResponseEntity<AccountDto> updateAccountDetails(@PathVariable Long accountId, @RequestBody AccountDto accountDto) throws InvalidAccountException{
		 AccountDto dto=accountservice.updateAccountDetails(accountId, accountDto);
		
		return new ResponseEntity<AccountDto>(dto, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}/balance")
	public ResponseEntity<Double> getBalance(@PathVariable Long id) throws InvalidAccountException {
	    double balance = accountservice.getBalance(id);
	    return ResponseEntity.ok(balance);
	}
	
}
