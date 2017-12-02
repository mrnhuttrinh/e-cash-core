package com.ecash.ecashcore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "account_type")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "typeCode")
public class AccountType extends Type {

  @OneToMany(mappedBy = "accountType")
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Account> account;

  public List<Account> getAccount() {
    return account;
  }

  public void setAccount(List<Account> account) {
    this.account = account;
  }
}
