package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.CurrencyCode;

public interface CurrencyCodeRepository extends JpaRepository<CurrencyCode, String> {
}
