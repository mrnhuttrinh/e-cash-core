package com.ecash.ecashcore.vo;

public class EcashExtendedInformationVO extends ExtendedInformationVO {
  private TerminalInformationVO additionalTerminalInfo;
  private TargetVO targetAccount;

  public TerminalInformationVO getAdditionalTerminalInfo() {
    return additionalTerminalInfo;
  }

  public void setAdditionalTerminalInfo(TerminalInformationVO additionalTerminalInfo) {
    this.additionalTerminalInfo = additionalTerminalInfo;
  }

  public TargetVO getTargetAccount() {
    return targetAccount;
  }

  public void setTargetAccount(TargetVO target) {
    this.targetAccount = target;
  }
}
