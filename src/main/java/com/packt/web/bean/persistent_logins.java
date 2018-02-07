package com.packt.web.bean;

import java.security.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="persistent_logins")
public class persistent_logins {

	
	@Id
	private String series;
	
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;
	
	private String username;
	
	public persistent_logins(){}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLast_used() {
		return last_used;
	}
	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}
	
	
	
}
