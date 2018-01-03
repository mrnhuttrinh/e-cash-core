package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecash.ecashcore.model.cms.MerchantStatement;

public interface MerchantStatementRepository extends CrudRepository<MerchantStatement, String>
{
  List<MerchantStatement> findByMerchantIdAndDueDateBetween(String merchantId, Date dueDateFrom,
      Date dueDateTo);
  MerchantStatement findFirstByMerchantIdOrderByDueDateDesc(String merchantId);
}
