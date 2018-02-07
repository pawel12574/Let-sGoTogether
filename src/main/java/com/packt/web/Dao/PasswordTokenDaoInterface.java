package com.packt.web.Dao;

import com.packt.web.bean.PasswordToken;

public interface PasswordTokenDaoInterface {

	public void save(PasswordToken passwordToken);
	public PasswordToken getToken(String token);
	public void remove(PasswordToken passToken);
	
	
	
}
