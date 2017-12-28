package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.AbstractHistory;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_history")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserHistory extends AbstractHistory<UserHistoryType> {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
  public UserHistory() {
    super();
  }

  public UserHistory(User user, User createdBy, UserHistoryType type, String details) {
    super(type);
    this.user = user;
    if (createdBy != null) {
      this.setCreatedBy(createdBy.getId());
    } else {
      this.setCreatedBy(null);
    }
    this.setDetails(details);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
