package com.ecash.ecashcore.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;

@Entity
@Table(name = "scms_sync_detail")
public class SCMSSyncDetail extends BaseUUID {
  public static final String SCMS_SYNC = "SCMS_SYNC";

  @Column(name = "personalization_code", nullable = false)
  private String personalizationCode;

  @Column(name = "sync_source")
  private String syncSource;

  @Column(name = "target_object", nullable = false)
  private String targetObject;
  
  @Column(name = "target_id", nullable = false)
  private String targetId;

  public SCMSSyncDetail() {
    super();
  }

  public SCMSSyncDetail(String personalizationCode, String syncSource, String targetObject, String targetId) {
    super();
    this.personalizationCode = personalizationCode;
    this.syncSource = syncSource;
    this.targetObject = targetObject;
    this.targetId = targetId;
  }

  public String getPersonalizationCode() {
    return personalizationCode;
  }

  public void setPersonalizationCode(String personalizationCode) {
    this.personalizationCode = personalizationCode;
  }

  public String getSyncSource() {
    return syncSource;
  }

  public void setSyncSource(String syncSource) {
    this.syncSource = syncSource;
  }

  public String getTargetObject() {
    return targetObject;
  }

  public void setTargetObject(String targetObject) {
    this.targetObject = targetObject;
  }

  public String getTargetId() {
    return targetId;
  }

  public void setTargetId(String targetId) {
    this.targetId = targetId;
  }
}
