package com.ecash.ecashcore.enums;

public enum HistoryTypeEnum {
  CREATED("CREATED"),
  DELETED("DELETED"),
  UPDATED("UPDATED");

  String name;

  private HistoryTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return this.toString();
  }
}
