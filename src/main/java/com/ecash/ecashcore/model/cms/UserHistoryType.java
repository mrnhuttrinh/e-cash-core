package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.AbstractHistoryType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_history_type")
public class UserHistoryType extends AbstractHistoryType {
  public static String PASSWORD_CHANGED = "PASSWORD_CHANGED";
}
