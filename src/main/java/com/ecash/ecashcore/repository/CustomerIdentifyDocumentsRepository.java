package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.CustomerIdentifyDocument;;

public interface CustomerIdentifyDocumentsRepository extends JpaRepository<CustomerIdentifyDocument, String> {

}