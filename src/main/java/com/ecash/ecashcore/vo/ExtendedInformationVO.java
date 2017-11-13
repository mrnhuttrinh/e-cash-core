package com.ecash.ecashcore.vo;

import java.util.Map;

public class ExtendedInformationVO {
  private String typeOfGoods; // Only for expense transaction
  private TerminalInformationVO additionalTerminalInfo;
  private Map<String, Object> transactionDetails;

  public String getTypeOfGoods() {
    return typeOfGoods;
  }

  public void setTypeOfGoods(String typeOfGoods) {
    this.typeOfGoods = typeOfGoods;
  }

  public TerminalInformationVO getAdditionalTerminalInfo() {
    return additionalTerminalInfo;
  }

  public void setAdditionalTerminalInfo(TerminalInformationVO additionalTerminalInfo) {
    this.additionalTerminalInfo = additionalTerminalInfo;
  }

  public Map<String, Object> getTransactionDetails() {
    return transactionDetails;
  }

  public void setTransactionDetails(Map<String, Object> transactionDetails) {
    this.transactionDetails = transactionDetails;
  }
}
