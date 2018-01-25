package com.ecash.ecashcore.enums;

public enum SCMSSyncTargetEnum {
  CUSTOMER("CUSTOMER"),
  ADDRESS("ADDRESS"),
  IDENTIFY_CARD("IDENTIFY_CARD"),
  PASSPORT_CARD("PASSPORT_CARD"),
  CARD("CARD");
  
  String name;

  private SCMSSyncTargetEnum(String name) {
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
