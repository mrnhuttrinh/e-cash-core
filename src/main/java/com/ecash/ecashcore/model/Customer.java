package com.ecash.ecashcore.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_code", nullable = false)
  private CustomerType customerType;

  @Column(name = "scms_member_code")
  private String scmsMemberCode;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "phone_1")
  private String phone1;

  @Column(name = "phone_2")
  private String phone2;

  private String email;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organization_id", nullable = true)
  private Organization organization;

  @Column(name = "date_of_birth")
  private Date dateOfBirth;

  private Integer gender;

  @Column(name = "date_became_customer")
  private Date dateBecameCustomer;

  private String status;

  @Column(name = "country_code")
  private String countryCode;

  private String occupation;

  private String title;

  private String position;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name="customer_address",
      joinColumns = @JoinColumn( name="customer_id"),
      inverseJoinColumns = @JoinColumn( name="address_id")
  )
  private Set<Address> addresses;

  @OneToMany(mappedBy="customer")
  private Set<Account> accounts;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public Set<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(Set<Account> accounts) {
    this.accounts = accounts;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }
}
