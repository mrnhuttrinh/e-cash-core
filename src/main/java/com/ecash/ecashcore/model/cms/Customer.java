package com.ecash.ecashcore.model.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ecash.ecashcore.model.BaseUUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "customer")
public class Customer extends BaseUUID {

  @Column(name = "scms_member_code", unique = true, nullable = false)
  private String scmsMemberCode;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "phone_1")
  private String phone1;

  @Column(name = "phone_2")
  private String phone2;

  @Column(name = "email")
  private String email;

  @Column(name = "date_of_birth")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOfBirth;

  @Column(name = "gender")
  private Integer gender;

  @Column(name = "date_became_customer")
  private Date dateBecameCustomer;

  @Column(name = "status")
  private String status;

  @Column(name = "country_code")
  private String countryCode;

  @Column(name = "occupation")
  private String occupation;

  @Column(name = "title")
  private String title;

  @Column(name = "position")
  private String position;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_code", nullable = false)
  private CustomerType customerType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organization_id", nullable = false)
  private Organization organization;

  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<Account> accounts;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "customers")
  private List<Address> addresses;

  @JsonProperty(access = Access.WRITE_ONLY)
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "customers")
  private List<IdentifyDocument> identifyDocuments;

  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<CustomerHistory> customerHistory;
  
  @ManyToMany(mappedBy = "customers")
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<User> users = new ArrayList<>();

  public CustomerType getCustomerType() {
    return customerType;
  }

  public void setCustomerType(CustomerType customerType) {
    this.customerType = customerType;
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

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Integer getGender() {
    return gender;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
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

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public List<IdentifyDocument> getIdentifyDocuments() {
    return identifyDocuments;
  }

  public void setIdentifyDocuments(List<IdentifyDocument> identifyDocuments) {
    this.identifyDocuments = identifyDocuments;
  }

  public List<CustomerHistory> getCustomerHistory() {
    return customerHistory;
  }

  public void setCustomerHistory(List<CustomerHistory> customerHistory) {
    this.customerHistory = customerHistory;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
