package com.ecash.ecashcore.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "currency_code")
public class CurrencyCode extends BaseModel {

  @Id
  @Column(name = "type_code", nullable = false)
  private String code;

  @Column(name = "text", nullable = true)
  private String text;

  @OneToMany(mappedBy = "currencyCode")
  private List<Account> account;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<Account> getAccount() {
    return account;
  }

  public void setAccount(List<Account> account) {
    this.account = account;
  }
}
