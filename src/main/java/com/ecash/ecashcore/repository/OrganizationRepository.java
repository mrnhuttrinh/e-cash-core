package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, String> {

}
