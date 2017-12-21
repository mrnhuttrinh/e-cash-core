package com.ecash.ecashcore.service;

import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.AccountHistory;
import com.ecash.ecashcore.model.cms.AccountHistoryType;
import com.ecash.ecashcore.repository.AccountHistoryRepository;
import com.ecash.ecashcore.repository.AccountHistoryTypeRepository;
import com.ecash.ecashcore.repository.AccountRepository;
import com.querydsl.core.types.Predicate;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;
  
  @Autowired
  AccountHistoryTypeRepository accountHistoryTypeRepository;

  @Autowired
  AccountHistoryRepository accountHistoryRepository;

  public List<Account> findAll() {
    return accountRepository.findAll();
  }
  
  public Iterable<Account> findAll(Predicate predicate, Pageable pageable) {
    return accountRepository.findAll(predicate, pageable);
  }

  public void updateAccount(Account newAccount, String userName) throws JSONException {
    Account account = accountRepository.getOne(newAccount.getId());
    if (account == null) {
      throw new ValidationException("Accoount is not exist.");
    }
    if (StatusEnum.getEnumByKey(newAccount.getStatus()) == null) {
      throw new ValidationException("Status is invalid.");
    }
    JSONObject previous = new JSONObject();
    previous.put("status", account.getStatus());
    JSONObject next = new JSONObject();
    next.put("status", newAccount.getStatus());
    AccountHistory history = new AccountHistory();
    history.setAccount(account);
    history.setStatus(StatusEnum.ACTIVE.toString());
    history.setDetails((new JSONObject()).put("previous", previous).put("next", next).toString());
    history.setCreatedBy(userName);
    AccountHistoryType accountHistoryType = accountHistoryTypeRepository.findOne(AccountHistoryType.UPDATED);
    history.setType(accountHistoryType);
    accountHistoryRepository.save(history);
    account.setStatus(newAccount.getStatus());
    accountRepository.save(account);
  }
  
}
