package com.ecash.ecashcore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "address_type")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "typeCode")
public class AddressType extends Type {

  @OneToMany(mappedBy = "addressType")
  @JsonProperty(access = Access.WRITE_ONLY)
  List<Address> addresses;

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }
}
