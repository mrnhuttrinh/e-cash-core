package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecash.ecashcore.model.cms.AccountHolder;

public interface AccountHolderRepository extends CrudRepository<AccountHolder, String>
{
  List<AccountHolder> findByHolderIdAndHolderType(String holderId, String holderType);

}
