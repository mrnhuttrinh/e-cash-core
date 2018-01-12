package com.ecash.ecashcore.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.util.DateTimeUtils;
import com.ecash.ecashcore.vo.ReportVO;

@Service
public class ReportService
{
  @Autowired
  private AccountHistoryService accountHistoryService;

  @Autowired
  private CustomerHistoryService customerHistoryService;

  @Autowired
  private CardHistoryService cardHistoryService;

  @Autowired
  private UserHistoryService userHistoryService;

  public ReportVO getReportGeneral(String fromDate, String toDate)
  {
    ReportVO result = new ReportVO();
    // Configure Date Range
    DateTime tmpFromDate = DateTimeUtils.parseDate(fromDate, DateTimeUtils.REPORT_PARAM_FORMAT,
        null);
    DateTime tmpToDate = DateTimeUtils.parseDate(toDate, DateTimeUtils.REPORT_PARAM_FORMAT,
        null).plusDays(1);
    result.setAccount(
        accountHistoryService.findByUpdateDate(tmpFromDate.toDate(), tmpToDate.toDate()));
    result.setCard(cardHistoryService.findByUpdateDate(tmpFromDate.toDate(), tmpToDate.toDate()));
    result.setCustomer(
        customerHistoryService.findByUpdateDate(tmpFromDate.toDate(), tmpToDate.toDate()));
    result.setUser(userHistoryService.findByUpdateDate(tmpFromDate.toDate(), tmpToDate.toDate()));
    return result;
  }
}
