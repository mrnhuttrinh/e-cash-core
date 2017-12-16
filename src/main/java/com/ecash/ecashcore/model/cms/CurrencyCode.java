package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "currency_code")
public class CurrencyCode extends BaseModel {

  @Id
  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "name", nullable = true)
  private String text;

  @OneToMany(mappedBy = "currencyCode")
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Account> account;

  public CurrencyCode() {
    super();
  }

  public CurrencyCode(String code) {
    super();
    this.code = code;
  }

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
