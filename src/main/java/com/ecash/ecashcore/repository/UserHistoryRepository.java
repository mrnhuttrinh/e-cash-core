package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.ecash.ecashcore.model.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, String>, QueryDslPredicateExecutor<UserHistory> {

}
