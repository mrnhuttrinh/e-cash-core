package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.AbstractHistoryType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_history_type")
public class AccountHistoryType extends AbstractHistoryType {
  public static String UPDATED = "UPDATED";
}
