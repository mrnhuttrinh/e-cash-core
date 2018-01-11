package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.CardHistory;
import com.ecash.ecashcore.model.cms.QCardHistory;

public interface CardHistoryRepository
    extends BaseQuerydslRepository<CardHistory, String, QCardHistory>
{
  @Query(value = "SELECT ah.history_type, count(ah) FROM card_history ah where created_at between :fromDate and :toDate group by ah.history_type", nativeQuery = true)
  List<Object[]> reportCardHistory(@Param("fromDate") Date fromDate,
      @Param("toDate") Date toDate);
}
