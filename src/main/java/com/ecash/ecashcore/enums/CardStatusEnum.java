package com.ecash.ecashcore.enums;

public enum CardStatusEnum {
  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE"),
  LOCKED("LOCKED"),
  VIOLATED("VIOLATED");

  String status;

  private CardStatusEnum(String status) {
    this.status = status;
  }

  public String getValue() {
    return this.toString();
  }

  public String getStatus() {
    return status;
  }

  public static CardStatusEnum findByStatus(String status) {
    for (CardStatusEnum value : values()) {
      if (value.getValue().equalsIgnoreCase(status)) {
        return value;
      }
    }
    return null;
  }
}
