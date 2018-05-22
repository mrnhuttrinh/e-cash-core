package com.ecash.ecashcore.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.AccountHistory;
import com.ecash.ecashcore.model.cms.AccountHistoryType;
import com.ecash.ecashcore.repository.AccountHistoryRepository;
import com.ecash.ecashcore.repository.AccountHistoryTypeRepository;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.AccountVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
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

  public void updateAccountStatus(Account newAccount, String userName) throws JSONException {
    Account account = accountRepository.findOne(newAccount.getId());

    if (account == null) {
      throw new ValidationException("Account is not exist.");
    }
    if (StatusEnum.getEnumByKey(newAccount.getStatus()) == null) {
      throw new ValidationException("Status is invalid.");
    }

    JSONObject previous = new JSONObject();
    previous.put(StringConstant.STATUS, account.getStatus());

    JSONObject next = new JSONObject();
    next.put(StringConstant.STATUS, newAccount.getStatus());

    JSONObject details = new JSONObject();
    details.put(StringConstant.PREVIOUS, previous).put(StringConstant.NEXT, next);

    AccountHistory history = new AccountHistory();
    history.setAccount(account);
    history.setStatus(StatusEnum.ACTIVE.toString());
    history.setDetails(details.toString());
    history.setCreatedBy(userName);
    String type = AccountHistoryType.LOCKED;
    if (newAccount.getStatus().equals(StatusEnum.ACTIVE.toString())) {
      type = AccountHistoryType.UNLOCKED;
    }
    AccountHistoryType accountHistoryType = accountHistoryTypeRepository.findOne(type);
    history.setType(accountHistoryType);
    accountHistoryRepository.save(history);

    account.setStatus(newAccount.getStatus());
    accountRepository.save(account);
  }

  public void lock(List<AccountVO> vos, Predicate predicate, Pageable pageable) {
    if ((vos == null || vos.isEmpty()) && predicate != null) {
      List<Account> accounts = (List<Account>) accountRepository.findAll(predicate);
      updateListAccountStatus(accounts, StatusEnum.DEACTIVE);
    } else if (vos != null) {
      for (AccountVO vo : vos) {
        if (StringUtils.isNullOrEmpty(vo.getId())) {
          throw new ValidationException("Account ids must not be null or emtpy.");
        }

        Account account = accountRepository.findOne(vo.getId());
        if (account == null) {
          throw new DataNotFoundException("Account could not be found. Id: " + vo.getId());
        }

        if (!account.getStatus().equals(StatusEnum.DEACTIVE.toString())) {
          account.setStatus(StatusEnum.DEACTIVE.toString());
          accountRepository.save(account);
        }
      }
    }
  }

  public void unlockAccounts(List<AccountVO> vos, Predicate predicate, Pageable pageable) {
    if ((vos == null || vos.isEmpty()) && predicate != null) {
      List<Account> accounts = (List<Account>) accountRepository.findAll(predicate);
      updateListAccountStatus(accounts, StatusEnum.ACTIVE);
    } else if (vos != null) {
      for (AccountVO vo : vos) {
        if (StringUtils.isNullOrEmpty(vo.getId())) {
          throw new ValidationException("Account ids must not be null or emtpy.");
        }

        Account account = accountRepository.findOne(vo.getId());
        if (account == null) {
          throw new DataNotFoundException("Account could not be found. Id: " + vo.getId());
        }

        if (!account.getStatus().equals(StatusEnum.ACTIVE.toString())) {
          account.setStatus(StatusEnum.ACTIVE.toString());
          accountRepository.save(account);
        }
      }
    }
  }

  private void updateListAccountStatus(List<Account> accounts, StatusEnum status) {
    for (Account account : accounts) {
      if (!account.getStatus().equals(status.toString())) {
        account.setStatus(status.toString());
        accountRepository.save(account);
      }
    }
  }
}
