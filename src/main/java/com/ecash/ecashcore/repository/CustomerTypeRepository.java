package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, String> {
}
