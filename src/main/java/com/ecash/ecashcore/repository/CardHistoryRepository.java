package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.CardHistory;

public interface CardHistoryRepository extends JpaRepository<CardHistory, String> {
}