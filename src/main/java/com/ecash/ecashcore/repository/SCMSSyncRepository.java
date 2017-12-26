package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QSCMSSync;
import com.ecash.ecashcore.model.cms.SCMSSync;

public interface SCMSSyncRepository extends BaseQuerydslRepository<SCMSSync, String, QSCMSSync> {

  public SCMSSync findBySyncCode(String syncCode);

}
