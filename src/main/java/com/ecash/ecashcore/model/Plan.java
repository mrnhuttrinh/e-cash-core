package com.ecash.ecashcore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="plan")
public class Plan extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type_code", nullable=true)
	private PlanTypes typeCode;
	
	private Integer limit;
	
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PlanTypes getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(PlanTypes typeCode) {
		this.typeCode = typeCode;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
