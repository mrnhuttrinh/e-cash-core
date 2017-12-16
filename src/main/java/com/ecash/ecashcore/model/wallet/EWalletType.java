package com.ecash.ecashcore.model.wallet;

import com.ecash.ecashcore.model.Type;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "e_wallet_type")
public class EWalletType extends Type {}
