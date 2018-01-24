package com.ecash.ecashcore.vo;

public class TargetVO {
  public static String ACCOUNT = "ACCOUNT";
  public static String WALLET = "WALLET";
  private String category;
  private String type;
  private String id;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isAccount() {
    return this.category.equalsIgnoreCase(ACCOUNT);
  }

  public boolean isWallet() {
    return this.category.equalsIgnoreCase(WALLET);
  }
}
