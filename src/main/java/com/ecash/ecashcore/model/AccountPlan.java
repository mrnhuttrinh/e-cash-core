package com.ecash.ecashcore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account_plan")
public class AccountPlan extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = true)
  private Account accountId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_id", nullable = true)
  private Plan planId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account getAccountId() {
    return accountId;
  }

  public void setAccountId(Account accountId) {
    this.accountId = accountId;
  }

  public Plan getPlanId() {
    return planId;
  }

  public void setPlanId(Plan planId) {
    this.planId = planId;
  }
}
