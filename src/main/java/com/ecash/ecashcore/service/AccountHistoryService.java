package com.ecash.ecashcore.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.AbstractHistoryType;
import com.ecash.ecashcore.repository.AccountHistoryRepository;
import com.ecash.ecashcore.repository.AccountHistoryTypeRepository;
import com.ecash.ecashcore.vo.ReportContent;

@Service
public class AccountHistoryService
{
  @Autowired
  AccountHistoryTypeRepository accountHistoryTypeRepository;

  @Autowired
  AccountHistoryRepository accountHistoryRepository;

  public ReportContent findByUpdateDate(Date fromDate, Date toDate)
  {
    List<Object[]> temp = accountHistoryRepository.reportAccountHistory(fromDate, toDate);
    ReportContent result = new ReportContent();
    for (Object[] row : temp)
    {
      if (AbstractHistoryType.CREATED.equalsIgnoreCase(row[0].toString()))
      {
        result.setCreate(Integer.parseInt(row[1].toString()));
      }
      else if (AbstractHistoryType.DELETED.equalsIgnoreCase(row[0].toString()))
      {
        result.setRemove(Integer.parseInt(row[1].toString()));
      }
      else if (AbstractHistoryType.LOCKED.equalsIgnoreCase(row[0].toString()))
      {
        result.setLock(Integer.parseInt(row[1].toString()));
      }
      else if (AbstractHistoryType.UNLOCKED.equalsIgnoreCase(row[0].toString()))
      {
        result.setUnLock(Integer.parseInt(row[1].toString()));
      }
    }
    return result;
  }

}
