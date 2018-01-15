package com.ecash.ecashcore.enums;

public enum CMSWalletStatusEnum {
  ACTIVE("ACTIVE"),
  INACTIVE("INACTIVE");

  String name;

  private CMSWalletStatusEnum(String name) {
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
