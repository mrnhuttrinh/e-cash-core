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
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id", nullable = true)
  private Account accountId;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "plan_id", nullable = true)
  private Plan planId;
}
