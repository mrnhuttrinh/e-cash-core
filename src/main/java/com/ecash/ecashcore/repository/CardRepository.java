package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Card;

public interface CardRepository extends JpaRepository<Card, String> {

  Card findByCardCode(String cardCode);
}
