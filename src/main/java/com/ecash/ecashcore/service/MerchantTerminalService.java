package com.ecash.ecashcore.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.MerchantTerminal;
import com.ecash.ecashcore.repository.MerchantTerminalRepository;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class MerchantTerminalService {

  @Autowired
  private MerchantTerminalRepository merchantTerminalRepository;

  public boolean checkMerchantPubKey(String pubKey) {
    Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(merchantTerminalRepository.findByPubKey(pubKey));
    return merchantTerminal.isPresent();
  }

  public MerchantTerminal createNewTerminal(MerchantTerminal newMerchantTerminal) {
    return merchantTerminalRepository.save(newMerchantTerminal);
  }

  public String getMerchantPubKey(String uuid) {
    Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(merchantTerminalRepository.findOne(uuid));
    if (merchantTerminal.isPresent()) {
      return merchantTerminal.get().getPubKey();
    }
    return null;
  }

  public MerchantTerminal updateMerchantTerminalPubKey(String uuid, String pubKey) {
    MerchantTerminal merchantTerminal = merchantTerminalRepository.findOne(uuid);
    merchantTerminal.setPubKey(pubKey);
    return merchantTerminalRepository.save(merchantTerminal);
  }

  public MerchantTerminal identifyValidMerchantTerminal(String terminalId) {
    Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(merchantTerminalRepository.findOne(terminalId));
    if (!merchantTerminal.isPresent()) {
      throw new ValidationException("Terminal id is not valid or not exist.");
    }

    return merchantTerminal.get();
  }
  
  public Iterable<MerchantTerminal> findAll(Predicate predicate, Pageable pageable) {
    return merchantTerminalRepository.findAll(predicate, pageable);
  }
}
