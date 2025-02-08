package com.bankService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankService.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
