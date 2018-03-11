package com.ecash.ecashcore.vo;

import com.ecash.ecashcore.enums.CardStatusEnum;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.Organization;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class PersonalizationVO implements ISyncableVO {

  private String personalizationCode;
  private Date personalizationDate;
  private String cardCode;
  private Date effectiveDate;
  private Date expiryDate;
  private Integer status;
  private String organizationCode;
  private String organizationShortName;
  private String memberCode;
  private String memberLastName;
  private String memberFirstName;
  private Integer gender;
  private Date birthdate;
  private String countryCode;
  private String identityCardNumber;
  private Date identityCardIssuingDate;
  private String identityCardIssuingAuthority;
  private String passportNumber;
  private Date passportIssuingDate;
  private Date passportExpiryDate;
  private String passportIssuingAuthority;
  private String temporaryAddress;
  private String permanentAddress;
  private String email;
  private String phoneNumber1;
  private String phoneNumber2;
  private String occupation;
  private String title;
  private String position;

  public String getPersonalizationCode() {
    return personalizationCode;
  }

  public void setPersonalizationCode(String personalizationCode) {
    this.personalizationCode = personalizationCode;
  }

  public Date getPersonalizationDate() {
    return personalizationDate;
  }

  public void setPersonalizationDate(Date personalizationDate) {
    this.personalizationDate = personalizationDate;
  }

  public String getCardCode() {
    return cardCode;
  }

  public void setCardCode(String cardCode) {
    this.cardCode = cardCode;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getOrganizationCode() {
    return organizationCode;
  }

  public void setOrganizationCode(String organizationCode) {
    this.organizationCode = organizationCode;
  }

  public String getOrganizationShortName() {
    return organizationShortName;
  }

  public void setOrganizationShortName(String organizationShortName) {
    this.organizationShortName = organizationShortName;
  }

  public String getMemberCode() {
    return memberCode;
  }

  public void setMemberCode(String memberCode) {
    this.memberCode = memberCode;
  }

  public String getMemberLastName() {
    return memberLastName;
  }

  public void setMemberLastName(String memberLastName) {
    this.memberLastName = memberLastName;
  }

  public String getMemberFirstName() {
    return memberFirstName;
  }

  public void setMemberFirstName(String memberFirstName) {
    this.memberFirstName = memberFirstName;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getIdentityCardNumber() {
    return identityCardNumber;
  }

  public void setIdentityCardNumber(String identityCardNumber) {
    this.identityCardNumber = identityCardNumber;
  }

  public Date getIdentityCardIssuingDate() {
    return identityCardIssuingDate;
  }

  public void setIdentityCardIssuingDate(Date identityCardIssuingDate) {
    this.identityCardIssuingDate = identityCardIssuingDate;
  }

  public String getIdentityCardIssuingAuthority() {
    return identityCardIssuingAuthority;
  }

  public void setIdentityCardIssuingAuthority(String identityCardIssuingAuthority) {
    this.identityCardIssuingAuthority = identityCardIssuingAuthority;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }

  public Date getPassportIssuingDate() {
    return passportIssuingDate;
  }

  public void setPassportIssuingDate(Date passportIssuingDate) {
    this.passportIssuingDate = passportIssuingDate;
  }

  public Date getPassportExpiryDate() {
    return passportExpiryDate;
  }

  public void setPassportExpiryDate(Date passportExpiryDate) {
    this.passportExpiryDate = passportExpiryDate;
  }

  public String getPassportIssuingAuthority() {
    return passportIssuingAuthority;
  }

  public void setPassportIssuingAuthority(String passportIssuingAuthority) {
    this.passportIssuingAuthority = passportIssuingAuthority;
  }

  public String getTemporaryAddress() {
    return temporaryAddress;
  }

  public void setTemporaryAddress(String temporaryAddress) {
    this.temporaryAddress = temporaryAddress;
  }

  public String getPermanentAddress() {
    return permanentAddress;
  }

  public void setPermanentAddress(String permanentAddress) {
    this.permanentAddress = permanentAddress;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber1() {
    return phoneNumber1;
  }

  public void setPhoneNumber1(String phoneNumber1) {
    this.phoneNumber1 = phoneNumber1;
  }

  public String getPhoneNumber2() {
    return phoneNumber2;
  }

  public void setPhoneNumber2(String phoneNumber2) {
    this.phoneNumber2 = phoneNumber2;
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

  @JsonIgnore
  public Card getCard() {
    Card card = new Card();
    card.setCardCode(cardCode);
    card.setEffectiveDate(effectiveDate);
    card.setExpiryDate(expiryDate);

    CardStatusEnum statusEnum = CardStatusEnum.findByStatusId(status);
    if (statusEnum == null) {
      statusEnum = CardStatusEnum.LOCKED;
    }

    card.setStatus(statusEnum.getStatus().toString());
    return card;
  }

  @JsonIgnore
  public Account getAccount() {
    Account account = new Account();
    account.setAccountName(email);
    return account;
  }

  @JsonIgnore
  public Customer getCustomer() {
    Customer customer = new Customer();
    customer.setScmsMemberCode(memberCode);
    customer.setFirstName(memberFirstName);
    customer.setLastName(memberLastName);
    customer.setGender(gender);
    customer.setDateOfBirth(birthdate);
    customer.setPhone1(phoneNumber1);
    customer.setPhone1(phoneNumber2);
    customer.setEmail(email);
    customer.setDateBecameCustomer(personalizationDate);
    customer.setCountryCode(countryCode);
    customer.setOccupation(occupation);
    customer.setTitle(title);
    customer.setPosition(position);
    return customer;
  }

  @JsonIgnore
  public Organization getOrganization() {
    Organization organization = new Organization();
    organization.setId(organizationCode);
    organization.setShortName(organizationShortName);
    return organization;
  }

  @JsonIgnore
  public Address getCustomerAddress() {
    Address address = new Address();
    address.setCountry(countryCode);
    address.setLine1(permanentAddress);
    address.setLine2(temporaryAddress);
    return address;
  }

  @JsonIgnore
  public IdentifyDocument getIdentifyCard() {
    IdentifyDocument identifyDocument = new IdentifyDocument();
    identifyDocument.setNumber(identityCardNumber);
    identifyDocument.setDateOfIssue(identityCardIssuingDate);
    identifyDocument.setPlaceOfIssue(identityCardIssuingAuthority);
    return identifyDocument;
  }

  @JsonIgnore
  public IdentifyDocument getPassportCard() {
    IdentifyDocument identifyDocument = new IdentifyDocument();
    identifyDocument.setNumber(passportNumber);
    identifyDocument.setDateOfIssue(passportIssuingDate);
    identifyDocument.setDateOfExpiry(passportExpiryDate);
    identifyDocument.setPlaceOfIssue(passportIssuingAuthority);
    return identifyDocument;
  }

  @JsonIgnore
  public void validate() {
    if (status == null) {
      throw new EcashException("Status must not be null");
    }
  }
}
