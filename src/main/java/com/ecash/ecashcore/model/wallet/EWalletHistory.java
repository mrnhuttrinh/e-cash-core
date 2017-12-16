package com.ecash.ecashcore.model.wallet;

import com.ecash.ecashcore.model.AbstractHistory;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "e_wallet_history")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EWalletHistory extends AbstractHistory<EWalletHistoryType> {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id", nullable = false)
  private EWallet wallet;

  public EWallet getWallet() {
    return wallet;
  }

  public void setWallet(EWallet wallet) {
    this.wallet = wallet;
  }
}
