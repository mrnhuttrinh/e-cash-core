package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel {
	@Column(name="created_at")
	protected Date createdAt;
	@Column(name="updated_at")
	protected Date updatedAt;
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
