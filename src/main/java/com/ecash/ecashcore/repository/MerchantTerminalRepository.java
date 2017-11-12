package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.MerchantTerminal;;

public interface MerchantTerminalRepository extends JpaRepository<MerchantTerminal, String> {
    public MerchantTerminal findByPubKey(String pubKey);
    public MerchantTerminal findById(String id);
}
