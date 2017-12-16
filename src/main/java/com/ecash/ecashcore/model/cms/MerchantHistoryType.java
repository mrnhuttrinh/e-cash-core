package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.AbstractHistoryType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "merchant_history_type")
public class MerchantHistoryType extends AbstractHistoryType{
}