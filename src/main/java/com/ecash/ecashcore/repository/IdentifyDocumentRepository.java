package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.QIdentifyDocument;

public interface IdentifyDocumentRepository
    extends BaseQuerydslRepository<IdentifyDocument, String, QIdentifyDocument> {

  IdentifyDocument findByNumber(String number);
}
