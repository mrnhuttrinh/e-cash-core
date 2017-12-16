package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CardHistoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHistoryTypeRepository extends JpaRepository<CardHistoryType, String> {
}
