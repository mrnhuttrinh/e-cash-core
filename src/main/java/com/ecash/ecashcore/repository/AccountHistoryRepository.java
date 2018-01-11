package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.AccountHistory;
import com.ecash.ecashcore.model.cms.QAccountHistory;

public interface AccountHistoryRepository
    extends BaseQuerydslRepository<AccountHistory, String, QAccountHistory>
{

  @Query(value = "SELECT ah.history_type, count(ah) FROM account_history ah where created_at between :fromDate and :toDate group by ah.history_type", nativeQuery = true)
  List<Object[]> reportAccountHistory(@Param("fromDate") Date fromDate,
      @Param("toDate") Date toDate);
}
