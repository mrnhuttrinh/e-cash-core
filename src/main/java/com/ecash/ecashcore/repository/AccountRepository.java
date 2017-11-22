package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

  @Query("SELECT o FROM Account o JOIN o.accountCards ac WHERE ac.card.id = :cardId AND o.accountType.typeCode = :accountType")
  List<Account> findByCardIdAndAccountType(@Param("cardId") String cardId, @Param("accountType") String accountType);
}
