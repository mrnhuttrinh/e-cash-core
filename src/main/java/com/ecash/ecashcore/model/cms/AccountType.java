package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "account_type")
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
