package com.ecash.ecashcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.repository.AccountRepository;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  public List<Account> findAll() {
    return accountRepository.findAll();
  }
}
