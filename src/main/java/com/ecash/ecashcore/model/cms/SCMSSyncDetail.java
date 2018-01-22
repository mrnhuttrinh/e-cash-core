package com.ecash.ecashcore.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecash.ecashcore.model.BaseModel;

@Entity
@Table(name = "scms_sync_detail")
public class SCMSSyncDetail extends BaseModel {
  public static final String SCMS_SYNC = "SCMS_SYNC";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "personalization_code", nullable = false)
  private String personalizationCode;

  @Column(name = "sync_source")
  private String syncSource;

  @Column(name = "target_object", nullable = false)
  private String targetObject;
  
  @Column(name = "target_id", nullable = false)
  private String targetId;

  public SCMSSyncDetail(String personalizationCode, String syncSource, String targetObject, String targetId) {
    super();
    this.personalizationCode = personalizationCode;
    this.syncSource = syncSource;
    this.targetObject = targetObject;
    this.targetId = targetId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
