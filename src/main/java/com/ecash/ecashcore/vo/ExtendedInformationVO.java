package com.ecash.ecashcore.vo;

public class ExtendedInformationVO {
  private TerminalInformationVO additionalTerminalInfo;
  private String transactionDetails;

  public TerminalInformationVO getAdditionalTerminalInfo() {
    return additionalTerminalInfo;
  }

  public void setAdditionalTerminalInfo(TerminalInformationVO additionalTerminalInfo) {
    this.additionalTerminalInfo = additionalTerminalInfo;
  }

  public String getTransactionDetails() {
    return transactionDetails;
  }

  public void transactionDetails(String transactionDetails) {
    this.transactionDetails = transactionDetails;
  }
}
