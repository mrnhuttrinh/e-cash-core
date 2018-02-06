package com.ecash.ecashcore.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "merchant_terminal")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "id")
public class MerchantTerminal extends BaseUUID {

  @Column(name = "pub_key", nullable = false)
  private String pubKey;

  @Column(name = "pub_key_expire_date", nullable = false)
  private Date pubKeyExpireDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;
  
  private String status;
  
  private String name;

  public String getPubKey() {
    return pubKey;
  }

  public void setPubKey(String pubKey) {
    this.pubKey = pubKey;
  }

  public Date getPubKeyExpireDate() {
    return pubKeyExpireDate;
  }

  public void setPubKeyExpireDate(Date pubKeyExpireDate) {
    this.pubKeyExpireDate = pubKeyExpireDate;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
