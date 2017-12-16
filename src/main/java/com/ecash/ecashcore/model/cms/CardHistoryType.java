package com.ecash.ecashcore.model.cms;


import com.ecash.ecashcore.model.AbstractHistoryType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "card_history_type")
public class CardHistoryType extends AbstractHistoryType{
}
