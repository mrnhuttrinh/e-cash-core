package com.ecash.ecashcore.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;

@Entity
@Table(name = "scms_sync")
public class SCMSSync extends BaseUUID {

  @Column(name = "sync_code", nullable = false, unique = true)
  private String syncCode;

  @Column(name = "sync_time")
  private Date syncTime;

  @Column(name = "finish_flag")
  private Boolean finishFlag;

  @Column(name = "sync_details")
  private String syncDetails;

  private String status;

  public String getSyncCode() {
    return syncCode;
  }

  public void setSyncCode(String syncCode) {
    this.syncCode = syncCode;
  }

  public Date getSyncTime() {
    return syncTime;
  }

  public void setSyncTime(Date syncTime) {
    this.syncTime = syncTime;
  }

  public Boolean getFinishFlag() {
    return finishFlag;
  }

  public void setFinishFlag(Boolean finishFlag) {
    this.finishFlag = finishFlag;
  }

  public String getSyncDetails() {
    return syncDetails;
  }

  public void setSyncDetails(String syncDetails) {
    this.syncDetails = syncDetails;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
