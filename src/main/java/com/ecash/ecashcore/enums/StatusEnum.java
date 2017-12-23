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

  @Override
  public String toString() {
    return this.name;
  }

  public static StatusEnum getEnumByKey(String key) {
    try {
      return StatusEnum.valueOf(key);
    } catch(Exception e) {
      return null;
    }
  }
}
