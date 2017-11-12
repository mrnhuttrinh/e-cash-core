package com.ecash.ecashcore.enums;

public enum AddressTypeEnum {
  DEFAULT("DEFAULT"),
  RESIDENT("RESIDENT"),
  TEMPORARY("TEMPORARY");

  String name;

  private AddressTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return this.toString();
  }
}
