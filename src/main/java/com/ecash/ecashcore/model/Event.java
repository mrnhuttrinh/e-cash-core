package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "event")
public class Event extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = true)
  private Account accountId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = true)
  private EventType typeCode;

  private Date date;

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

  public EventType getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(EventType typeCode) {
    this.typeCode = typeCode;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
