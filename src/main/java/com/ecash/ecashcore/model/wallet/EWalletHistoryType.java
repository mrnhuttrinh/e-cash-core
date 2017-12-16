package com.ecash.ecashcore.model.wallet;

import com.ecash.ecashcore.model.AbstractHistoryType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "e_wallet_history_type")
public class EWalletHistoryType extends AbstractHistoryType{
}
