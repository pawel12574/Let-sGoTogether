package com.packt.web.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="taxi")
public class Taxi extends AbstractEntity{


	 @NotEmpty
	 private String phoneNumber;
	 
	 @NotEmpty
	 private String name;
	 
	 @CreationTimestamp
	 @Temporal(TemporalType.TIMESTAMP)
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm")
	 private Date created;
	 
	 @Transient
	 String createdDate;
	 
	 public Taxi(){
		 
	 }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	 
}
