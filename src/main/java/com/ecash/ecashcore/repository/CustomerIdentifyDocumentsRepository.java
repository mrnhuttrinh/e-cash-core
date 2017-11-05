package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.CustomerIdentifyDocuments;;

public interface CustomerIdentifyDocumentsRepository extends JpaRepository<CustomerIdentifyDocuments, String> {

}
