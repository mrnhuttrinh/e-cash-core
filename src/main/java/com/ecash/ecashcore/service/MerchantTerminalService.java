package com.ecash.ecashcore.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.MerchantTerminal;
import com.ecash.ecashcore.repository.MerchantTerminalRepository;

@Service
@Transactional
public class MerchantTerminalService {

    @Autowired
    private MerchantTerminalRepository merchantTerminalRepository;

    public String findSecretKeyByMerchantPubKey(String pubKey) {
        Optional<MerchantTerminal> merchantTerminal = Optional
                .ofNullable(merchantTerminalRepository.findByPubKey(pubKey));
        if (merchantTerminal.isPresent()) {
            return merchantTerminal.get().getPubKey();
        } else {
            return null;
        }
    }

    public boolean checkMerchantPubKey(String pubKey) {
        Optional<MerchantTerminal> merchantTerminal = Optional
                .ofNullable(merchantTerminalRepository.findByPubKey(pubKey));
        if (merchantTerminal.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

}
