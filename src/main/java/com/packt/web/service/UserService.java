package com.packt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.PasswordTokenDao;
import com.packt.web.Dao.PasswordTokenDaoInterface;
import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.PasswordToken;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

@Service
@Transactional
public class UserService implements UserServiceInterface {

	@Autowired
	UserDaoInterface userDao;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	PasswordTokenDaoInterface passwordTokenDao;
	
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
	public User getUser(int id){
		return userDao.getUser(id);
	}
	
	public User getLoggedUserFromContext(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public User getLoggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println(username);
		return userDao.getUser(username);
	}
	
	public String getLoggedUserName(){
		return userDao.getLoggedUserName();
	}
	
	public void removeUser(int id){
		User user = userDao.getUser(id);
		userDao.removeUser(user);
	}

	public void mergeUser(User user){
	    userDao.mergeUser(user);	
	}
	
	public String checkEmailExist(String username) {
		return userDao.getEmailByString(username);//username as email
		
	}
	
	public List<Trip> getMyOwnTrip() {
		return userDao.getMyOwnTrip();
	}

	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.addUser(user); //add user entity
	}
	
	public boolean matchPassword(String password, User user){
		
		return passwordEncoder.matches(password, user.getPassword());
	}

	public void addTriptoUserList(Trip trip) {
		User user=this.getLoggedUser();
		user.getMyTrip().add(trip);
		userDao.mergeUser(user);    //add trip to user entity
	}

	public void merge(User user){
		userDao.mergeUser(user);
	}
	
	public void savePasswordToken(User user, String token){
	    PasswordToken passwordToken = new PasswordToken(token, user);
	    //passwordToken.setExpiryDate(expiryDate); jakis dac trzeba 2 min?
        passwordTokenDao.save(passwordToken);
	}
	
	public PasswordToken getToken(String token){
		return passwordTokenDao.getToken(token);
	}
	
	public void deleteToken(PasswordToken passToken){
		passwordTokenDao.remove(passToken);
	}
	
	public void changePassword(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.updatePassword(user);
	}
	
	public void changePassword(String newpassword, User user){
		user.setPassword(passwordEncoder.encode(newpassword));
		userDao.updatePassword(user);
	}
	
	public List<User> getAllUsers(){
		 return userDao.getAllUsers();
	}
	
}
