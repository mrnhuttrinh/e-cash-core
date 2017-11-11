package com.ecash.ecashcore.vo;

import java.util.Map;

public class TerminalAddressVO {
  private String addressType;
  private Map<String, Object> details;

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  public Map<String, Object> getDetails() {
    return details;
  }

  public void setDetails(Map<String, Object> details) {
    this.details = details;
  }
}
