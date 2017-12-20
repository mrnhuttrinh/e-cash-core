package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.IdentifyDocument;
public interface IdentifyDocumentRepository extends JpaRepository<IdentifyDocument, String> {
  
  IdentifyDocument findByNumber(String number);
}
