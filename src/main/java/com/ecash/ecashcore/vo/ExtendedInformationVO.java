package com.ecash.ecashcore.vo;

public class ExtendedInformationVO {
  private TerminalInformationVO additionalTerminalInfo;
  private String transactionDetails;
  private TargetVO targetAccount;

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

  public TargetVO getTargetAccount() {
    return targetAccount;
  }

  public void setTargetAccount(TargetVO target) {
    this.targetAccount = target;
  }
}
