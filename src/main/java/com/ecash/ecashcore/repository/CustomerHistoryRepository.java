package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.CustomerHistory;
import com.ecash.ecashcore.model.cms.QCustomerHistory;

public interface CustomerHistoryRepository
    extends BaseQuerydslRepository<CustomerHistory, String, QCustomerHistory>
{
  @Query(value = "SELECT ah.history_type, count(ah) FROM customer_history ah where created_at between :fromDate and :toDate group by ah.history_type", nativeQuery = true)
  List<Object[]> reportCustomerHistory(@Param("fromDate") Date fromDate,
      @Param("toDate") Date toDate);
}
