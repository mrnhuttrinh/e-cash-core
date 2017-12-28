package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecash.ecashcore.model.cms.CardStatement;

public interface CardStatementRepository extends CrudRepository<CardStatement, String>
{
  List<CardStatement> findByCardCardNumberAndDueDateBetween(String cardNumber, Date dueDateFrom,
      Date dueDateTo);
}
