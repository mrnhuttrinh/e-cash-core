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

    public boolean checkMerchantPubKey(String pubKey) {
        Optional<MerchantTerminal> merchantTerminal = Optional
                .ofNullable(merchantTerminalRepository.findByPubKey(pubKey));
        return merchantTerminal.isPresent();
    }

    public MerchantTerminal createNewTerminal(MerchantTerminal newMerchantTerminal) {
        return merchantTerminalRepository.save(newMerchantTerminal);
    }

    public String getMerchantPubKey(String uuid) {
        Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(merchantTerminalRepository.findById(uuid));
        if (merchantTerminal.isPresent()) {
            return merchantTerminal.get().getPubKey();
        }
        return null;
    }

    public MerchantTerminal updateMerchantTerminalPubKey(String uuid, String pubKey) {
        MerchantTerminal merchantTerminal = merchantTerminalRepository.findById(uuid);
        merchantTerminal.setPubKey(pubKey);
        return merchantTerminalRepository.save(merchantTerminal);
    }

}
