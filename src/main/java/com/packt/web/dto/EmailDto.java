package com.packt.web.dto;

public class EmailDto {

	
	private String username;

	public EmailDto()
	{}
	
	public EmailDto(String username) {
		
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}

   