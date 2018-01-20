package com.ecash.ecashcore.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.CustomerHistory;
import com.ecash.ecashcore.model.cms.CustomerType;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.Organization;
import com.ecash.ecashcore.model.cms.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CustomerTransactionVO {
  private String id;

  private String scmsMemberCode;

  private String firstName;

  private String lastName;

  private String phone1;

  private String phone2;

  private String email;

  private Date dateOfBirth;

  private Integer gender;

  private Date dateBecameCustomer;

  private String status;

  private String countryCode;

  private String occupation;

  private String title;

  private String position;
  
  private List<TransactionVO> transactions;
  
  public CustomerTransactionVO() {
    super();
    this.transactions = new ArrayList<TransactionVO>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getScmsMemberCode() {
    return scmsMemberCode;
  }

  public void setScmsMemberCode(String scmsMemberCode) {
    this.scmsMemberCode = scmsMemberCode;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone1() {
    return phone1;
  }

  public void setPhone1(String phone1) {
    this.phone1 = phone1;
  }

  public String getPhone2() {
    return phone2;
  }

  public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getDateBecameCustomer() {
    return dateBecameCustomer;
  }

  public void setDateBecameCustomer(Date dateBecameCustomer) {
    this.dateBecameCustomer = dateBecameCustomer;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public List<TransactionVO> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<TransactionVO> transactions) {
    this.transactions = transactions;
  }
}
