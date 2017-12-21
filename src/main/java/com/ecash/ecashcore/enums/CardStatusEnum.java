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

  @Override
  public String toString() {
    return this.status;
  }

  public String getStatus() {
    return this.status;
  }

  public static CardStatusEnum findByStatus(String status) {
    for (CardStatusEnum value : values()) {
      if (value.getStatus().equalsIgnoreCase(status)) {
        return value;
      }
    }
    return null;
  }
}
