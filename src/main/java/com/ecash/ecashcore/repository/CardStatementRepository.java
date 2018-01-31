package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import com.ecash.ecashcore.model.cms.CardStatement;

public interface CardStatementRepository extends BaseRepository<CardStatement, String>
{
  List<CardStatement> findByCardCardNumberAndDueDateBetween(String cardNumber, Date dueDateFrom,
      Date dueDateTo);
}
