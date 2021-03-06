package com.ecash.ecashcore.enums;

public enum AccountTypeEnum {
  DEFAULT("DEFAULT"),
  DEBIT("DEBIT");

  String name;

  private AccountTypeEnum(String name) {
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
