package com.ecash.ecashcore.vo;

import java.util.Date;
import java.util.List;

import com.ecash.ecashcore.model.cms.SCMSSync;
import com.ecash.ecashcore.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SyncV1VO {

  private String syncCode;
  private Date syncTime;
  private Boolean finishFlag;
  
  private List<PersonalizationVO> personalizations;

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

  @JsonIgnore
  public SCMSSync getSCMSSync() {
    SCMSSync scmsSync = new SCMSSync();
    scmsSync.setSyncCode(syncCode);
    scmsSync.setSyncTime(syncTime);
    scmsSync.setFinishFlag(finishFlag);
    scmsSync.setSyncDetails(JsonUtils.objectToJsonString(this));
    return scmsSync;
  }

  public List<PersonalizationVO> getPersonalizations() {
    return personalizations;
  }

  public void setPersonalizations(List<PersonalizationVO> personalizations) {
    this.personalizations = personalizations;
  }
}
