package com.ecash.ecashcore.enums;

public enum EWalletTypeEnum {
  DEFAULT("DEFAULT");

  String name;

  private EWalletTypeEnum(String name) {
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
