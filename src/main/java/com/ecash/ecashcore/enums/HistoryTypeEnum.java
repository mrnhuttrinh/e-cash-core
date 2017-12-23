package com.ecash.ecashcore.enums;

public enum HistoryTypeEnum {
//  ADDED("ADDED"),
//  CREATED("CREATED"),
//  DELETED("DELETED"),
//  LOCKED("LOCKED"),
//  PASSWORD_CHANGED("PASSWORD_CHANGED"),
//  UNLOCKED("UNLOCKED");
  TA("TA");

  String name;

  private HistoryTypeEnum(String name) {
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
