package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
