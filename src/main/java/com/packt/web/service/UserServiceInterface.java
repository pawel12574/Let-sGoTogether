package com.packt.web.service;

import java.util.List;

import com.packt.web.bean.PasswordToken;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

public interface UserServiceInterface {
	
	public User getUser(String login);
	public User getUser(int id);
	public User getLoggedUser();
	public User getLoggedUserFromContext();
	public String getLoggedUserName();
	public void removeUser(int id);
	public void mergeUser(User user);
	public String checkEmailExist(String email);
	public List<Trip> getMyOwnTrip();
    public void createUser(User user);
    public boolean matchPassword(String password, User user);
    public void addTriptoUserList(Trip trip);
    public void merge(User user);
	public void savePasswordToken(User user, String token);
	public PasswordToken getToken(String token);
	public void deleteToken(PasswordToken passToken);
	public void changePassword(User user);
	public void changePassword(String newpassword, User user);
	public List<User> getAllUsers();
	
}
