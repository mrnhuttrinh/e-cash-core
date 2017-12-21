package com.ecash.ecashcore.enums;

public enum PlanTypeEnum {
  DEFAULT("DEFAULT");

  String name;

  private PlanTypeEnum(String name) {
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
