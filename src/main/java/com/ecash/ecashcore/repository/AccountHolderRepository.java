package com.ecash.ecashcore.repository;

import java.util.List;

import com.ecash.ecashcore.model.cms.AccountHolder;

public interface AccountHolderRepository extends BaseRepository<AccountHolder, String>
{
  List<AccountHolder> findByHolderIdAndHolderType(String holderId, String holderType);

}
