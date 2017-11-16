package com.ecash.ecashcore.enums;

public enum StatusEnum {
  ACTIVE("ACTIVE"),
  CLOSE("CLOSE"),
  DELETE("DELETE"),
  DEACTIVE("DEACTIVE");

  String name;

  private StatusEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return this.toString();
  }
}