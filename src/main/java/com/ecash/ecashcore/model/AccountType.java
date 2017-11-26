package com.ecash.ecashcore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "account_type")
public class AccountType extends Type {

  @OneToMany(mappedBy = "accountType")
  @JsonBackReference
  private List<Account> account;

  public List<Account> getAccount() {
    return account;
  }

  public void setAccount(List<Account> account) {
    this.account = account;
  }
}
