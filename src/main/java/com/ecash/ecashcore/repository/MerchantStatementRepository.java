package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import com.ecash.ecashcore.model.cms.MerchantStatement;
import com.ecash.ecashcore.model.cms.QMerchantStatement;

public interface MerchantStatementRepository
    extends BaseQuerydslRepository<MerchantStatement, String, QMerchantStatement>
{
  List<MerchantStatement> findByMerchantIdAndDueDateBetween(String merchantId, Date dueDateFrom,
      Date dueDateTo);

  List<MerchantStatement> findByDueDateBetweenAndIsSettlement(Date dueDateFrom, Date dueDateTo, boolean isSettlement);

  MerchantStatement findFirstByMerchantIdOrderByDueDateDesc(String merchantId);
}
