package com.ecash.ecashcore.enums;

public enum CurrencyCodeEnum {
  VND("vnd");

  String name;

  private CurrencyCodeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return this.getName();
  }
}
