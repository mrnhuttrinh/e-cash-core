package com.ecash.ecashcore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.QWallet;
import com.ecash.ecashcore.model.cms.Wallet;
import com.querydsl.core.types.Predicate;

public interface WalletRepository extends BaseQuerydslRepository<Wallet, String, QWallet>, QueryByExampleExecutor<Wallet> {
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'WALLET_LIST/VIEW')")
  public Page<Wallet> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<Wallet> findAll(Pageable pageable);
}
