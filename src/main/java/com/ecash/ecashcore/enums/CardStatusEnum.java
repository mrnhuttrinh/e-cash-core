package com.ecash.ecashcore.enums;

public enum CardStatusEnum {
  ACTIVE("ACTIVE", 1),
  CANCELED("CANCELED", 2),
  LOCKED("LOCKED", 3),
  VIOLATED("VIOLATED", 4);

  String status;
  int statusId;

  private CardStatusEnum(String status, int statusId) {
    this.status = status;
    this.statusId = statusId;
  }

  @Override
  public String toString() {
    return this.status;
  }

  public String getStatus() {
    return this.status;
  }

  public int getStatusId() {
    return this.statusId;
  }

  public static CardStatusEnum findByStatus(String status) {
    for (CardStatusEnum value : values()) {
      if (value.getStatus().equalsIgnoreCase(status)) {
        return value;
      }
    }
    return null;
  }

  public static CardStatusEnum findByStatusId(int statusId) {
    for (CardStatusEnum value : values()) {
      if (value.getStatusId() == statusId) {
        return value;
      }
    }
    return null;
  }
}
