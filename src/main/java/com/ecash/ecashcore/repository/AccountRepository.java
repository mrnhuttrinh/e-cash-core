package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.Account;

@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public interface AccountRepository extends JpaRepository<Account, String> {

  @Query("SELECT o FROM Account o JOIN o.cards c WHERE c.cardNumber = :cardId AND o.accountType.typeCode = :accountType")
  List<Account> findByCardIdAndAccountType(@Param("cardId") String cardId, @Param("accountType") String accountType);
}
