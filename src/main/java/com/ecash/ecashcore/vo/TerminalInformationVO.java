package com.ecash.ecashcore.vo;

public class TerminalInformationVO {
  private String terminalId;
  private TerminalAddressVO terminalAddress;

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public TerminalAddressVO getTerminalAddress() {
    return terminalAddress;
  }

  public void setTerminalAddress(TerminalAddressVO terminalAddress) {
    this.terminalAddress = terminalAddress;
  }
}
