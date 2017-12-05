package com.ecash.ecashcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.repository.AccountRepository;
import com.querydsl.core.types.Predicate;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  public List<Account> findAll() {
    return accountRepository.findAll();
  }
  
  public Iterable<Account> findAll(Predicate predicate, Pageable pageable) {
    return accountRepository.findAll(predicate, pageable);
  }
}
