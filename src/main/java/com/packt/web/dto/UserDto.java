package com.packt.web.dto;

import java.util.Date;

public class UserDto {
	
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date birthDate;
	private String phoneNumber;

	
	public UserDto(){}
	
	
	public UserDto(String name, String username, String surname, String password, Date birthDate, String phoneNumber) {
		
		this.name=name;
		this.username = username;
		this.surname = surname;
		this.password = password;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	


}
