package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.cms.QCustomerIdentifyDocument;;

public interface CustomerIdentifyDocumentsRepository
    extends BaseQuerydslRepository<CustomerIdentifyDocument, String, QCustomerIdentifyDocument> {

}
