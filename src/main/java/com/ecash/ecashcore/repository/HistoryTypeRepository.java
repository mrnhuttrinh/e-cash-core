package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.HistoryType;

public interface HistoryTypeRepository extends JpaRepository<HistoryType, String> {
}
