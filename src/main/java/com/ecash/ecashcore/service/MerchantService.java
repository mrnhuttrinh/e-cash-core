package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.repository.MerchantRepository;
import com.querydsl.core.types.Predicate;

@Service
public class MerchantService {
  @Autowired
  MerchantRepository merchantRepository;
  
  public Iterable<Merchant> findAll(Predicate predicate, Pageable pageable) {
    return merchantRepository.findAll(predicate, pageable);
  }
}
