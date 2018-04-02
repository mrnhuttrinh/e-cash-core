package com.ecash.ecashcore.enums;

public enum TransactionTypeEnum {
  DEPOSIT("DEPOSIT"),
  EXPENSE("EXPENSE"),
  PAYMENT("PAYMENT"),
  TRANSFER("TRANSFER"),
  TRANSFER_OUT("TRANSFER_OUT"),
  WITHDRAW("WITHDRAW"),
  REFUND("REFUND");

  String name;

  private TransactionTypeEnum(String name) {
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
