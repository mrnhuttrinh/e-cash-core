package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.IdentifyDocumentType;
import com.ecash.ecashcore.model.cms.QIdentifyDocumentType;

public interface IdentifyDocumentTypeRepository
    extends BaseQuerydslRepository<IdentifyDocumentType, String, QIdentifyDocumentType> {

  IdentifyDocumentType findByTypeCode(String typeCode);
}
