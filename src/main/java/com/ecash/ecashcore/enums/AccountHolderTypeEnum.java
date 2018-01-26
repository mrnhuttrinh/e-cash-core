package com.ecash.ecashcore.enums;

public enum AccountHolderTypeEnum {
  CUSTOMER("CUSTOMER"),
  MERCHANT("MERCHANT");

  String name;

  private AccountHolderTypeEnum(String name) {
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
