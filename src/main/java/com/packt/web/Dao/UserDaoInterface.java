package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Trip;
import com.packt.web.bean.User;



public interface UserDaoInterface {

	public void addUser(User user);
	public void mergeUser(User user);
	public void removeUser(User user);
	public String getLoggedUserName();
	public User getUser(String login);
	public User getUser(int id);
	public String getEmailByString(String email);
	public List<Trip> getMyOwnTrip();
	public void updatePassword(User user);
	public List<User> getAllUsers();

	
}
