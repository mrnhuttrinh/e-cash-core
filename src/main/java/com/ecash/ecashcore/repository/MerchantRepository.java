package com.ecash.ecashcore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.QMerchant;
import com.querydsl.core.types.Predicate;

public interface MerchantRepository extends BaseQuerydslRepository<Merchant, String, QMerchant> {
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'MERCHANT_LIST/VIEW')")
  public Page<Merchant> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<Merchant> findAll(Pageable pageable);
}
