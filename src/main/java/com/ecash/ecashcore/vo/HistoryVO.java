package com.ecash.ecashcore.vo;

import java.util.HashMap;
import java.util.Map;

public class HistoryVO {

  private Map<String, Object> previous = new HashMap<>();

  private Map<String, Object> next = new HashMap<>();

  public Map<String, Object> getPrevious() {
    return previous;
  }

  public void setPrevious(Map<String, Object> previous) {
    this.previous = previous;
  }

  public Map<String, Object> getNext() {
    return next;
  }

  public void setNext(Map<String, Object> next) {
    this.next = next;
  }
}
