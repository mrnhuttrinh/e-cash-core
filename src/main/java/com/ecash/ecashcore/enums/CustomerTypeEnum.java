package com.ecash.ecashcore.enums;

public enum CustomerTypeEnum {
  DEFAULT("DEFAULT"),
  STAFF("STAFF"),
  STUDENT("STUDENT");

  String name;

  private CustomerTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return this.toString();
  }
}
