package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "address_type")
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
