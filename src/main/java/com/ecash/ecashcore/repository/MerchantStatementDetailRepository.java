package com.ecash.ecashcore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecash.ecashcore.model.cms.MerchantStatementDetail;

public interface MerchantStatementDetailRepository
    extends CrudRepository<MerchantStatementDetail, String>
{

}
