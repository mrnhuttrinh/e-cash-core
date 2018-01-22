package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QSCMSSyncDetail;
import com.ecash.ecashcore.model.cms.SCMSSyncDetail;

public interface SCMSSyncDetailRepository extends BaseQuerydslRepository<SCMSSyncDetail, String, QSCMSSyncDetail> {

  public SCMSSyncDetail findByPersonalizationCode(String personalizationCode);
  
  public SCMSSyncDetail findByPersonalizationCodeAndTargetObject(String personalizationCode, String targetObject);

}
