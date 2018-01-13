package com.ecash.ecashcore.enums;

public enum CMSWalletTypeEnum {
  DEFAULT("DEFAULT");

  String name;

  private CMSWalletTypeEnum(String name) {
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
