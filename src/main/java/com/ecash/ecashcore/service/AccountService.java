package com.ecash.ecashcore.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.enums.HistoryTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountHistory;
import com.ecash.ecashcore.model.HistoryType;
import com.ecash.ecashcore.repository.AccountHistoryRepository;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.HistoryTypeRepository;
import com.querydsl.core.types.Predicate;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;
  
  @Autowired
  HistoryTypeRepository historyTypeRepository;
  
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
    history.setStatus(StatusEnum.ACTIVE.getValue());
    history.setDetails((new JSONObject()).put("previous", previous).put("next", next).toString());
    history.setCreatedBy(userName);
    HistoryType historyType = historyTypeRepository.findOne(HistoryTypeEnum.UPDATED.toString());
    history.setType(historyType);
    accountHistoryRepository.save(history);
    account.setStatus(newAccount.getStatus());
    accountRepository.save(account);
  }
  
}
