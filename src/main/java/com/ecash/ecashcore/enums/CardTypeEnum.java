package com.ecash.ecashcore.enums;

public enum CardTypeEnum {
  DEFAULT("DEFAULT");

  String name;

  private CardTypeEnum(String name) {
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
