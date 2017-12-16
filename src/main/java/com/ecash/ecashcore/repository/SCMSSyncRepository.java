package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.SCMSSync;

public interface SCMSSyncRepository extends JpaRepository<SCMSSync, String> {

  public SCMSSync findBySyncCode(String syncCode);

}
