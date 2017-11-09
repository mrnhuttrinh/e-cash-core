package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.CardType;

public interface CardTypeRepository extends JpaRepository<CardType, String> {

}
