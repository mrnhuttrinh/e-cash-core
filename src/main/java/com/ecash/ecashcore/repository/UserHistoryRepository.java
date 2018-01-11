package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.QUserHistory;
import com.ecash.ecashcore.model.cms.UserHistory;

public interface UserHistoryRepository
    extends BaseQuerydslRepository<UserHistory, String, QUserHistory>
{
  @Query(value = "SELECT ah.history_type, count(ah) FROM user_history ah where created_at between :fromDate and :toDate group by ah.history_type", nativeQuery = true)
  List<Object[]> reportUserHistory(@Param("fromDate") Date fromDate,
      @Param("toDate") Date toDate);
}
