package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.MerchantTerminal;
import com.ecash.ecashcore.model.cms.QMerchantTerminal;
import com.querydsl.core.types.Predicate;

public interface MerchantTerminalRepository
    extends BaseQuerydslRepository<MerchantTerminal, String, QMerchantTerminal> {

  public MerchantTerminal findByPubKey(String pubKey);

  public List<MerchantTerminal> findByMerchant(Merchant merchant);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'MERCHANT_TERMINAL_LIST/VIEW')")
  public Page<MerchantTerminal> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<MerchantTerminal> findAll(Pageable pageable);
}
