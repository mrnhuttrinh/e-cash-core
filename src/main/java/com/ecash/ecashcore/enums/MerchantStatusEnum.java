package com.ecash.ecashcore.enums;

public enum MerchantStatusEnum {
  STORAGE("STORAGE"),
  PENDING("PENDING");

  String name;

  private MerchantStatusEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
