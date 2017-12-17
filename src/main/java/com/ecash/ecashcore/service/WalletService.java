package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.model.cms.Wallet;
import com.ecash.ecashcore.repository.WalletRepository;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class WalletService {

  @Autowired
  WalletRepository walletRepository;


  public Iterable<Wallet> findAll(Predicate predicate, Pageable pageable) {
    return walletRepository.findAll(predicate, pageable);
  }

}
