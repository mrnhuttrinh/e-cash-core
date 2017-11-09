package com.ecash.ecashcore.enums;

public enum TransactionTypeEnum {
  DEPOSIT("DEPOSIT"),
  EXPENSE("EXPENSE"),
  PAYMENT("PAYMENT");

  String name;

  private TransactionTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
