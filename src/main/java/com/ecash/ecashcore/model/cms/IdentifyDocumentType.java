package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "identify_document_type")
public class IdentifyDocumentType extends Type {

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "identifyDocumentType")
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<IdentifyDocument> identifyDocuments;

  public List<IdentifyDocument> getIdentifyDocuments() {
    return identifyDocuments;
  }

  public void setIdentifyDocuments(List<IdentifyDocument> identifyDocuments) {
    this.identifyDocuments = identifyDocuments;
  }

}
