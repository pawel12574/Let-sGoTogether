package com.packt.web.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Role;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;
import com.packt.web.service.DateService;

@Repository
public class UserDao implements UserDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional                        //(propagation = Propagation.REQUIRES_NEW)
	public void addUser(User user) {
		
		entityManager.persist(user);
	}
	
	@Transactional
	public void updatePassword(User user){
		Query query = entityManager.createQuery("update User set password='"+user.getPassword()+"' where id="+user.getId()+"");
		query.executeUpdate();
	}

	@Transactional
	public void mergeUser(User user) {
		entityManager.merge(user);
	}
	
	@Transactional
	public void removeUser(User user){
		
		entityManager.remove(user);
	}
	
	public String getLoggedUserName(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return username;
	}

	@Transactional
	public User getUser(String username) {  // nie dzia³a gdy wywo³uje sie metode w usercontroller change password
		Query query = entityManager.createQuery("select u from User u left join fetch u.role where username='" + username + "'");

		List<User> result = new ArrayList<User>();
		result = query.getResultList();
		if (result.size() > 0){
		//	result.get(0).setAge(DateService.getAge(result.get(0).getBirthDate()));
			return result.get(0);
			}
		else
			return null;

	}
	
	@Transactional
	public User getUser(int id) {
		Query query = entityManager.createQuery("select u from User u where u.id="+id+"");
		User user=(User) query.getResultList().get(0);
		user.setAge(DateService.getAge(user.getBirthDate()));
		return user;
		
    }
	
	@Transactional
	public String getEmailByString(String username){
		Query query = entityManager.createQuery("select u from User u where u.username='"+username+"'");//username as email
		List<User> result=query.getResultList();
		
		if(result.isEmpty())
		   return null;
		else return result.get(0).getUsername();
	}
	

	@Transactional
	public List<Trip> getMyOwnTrip() {
		
        Query query = entityManager.createQuery(
				"select t from Trip t where user.id=(select id from User where username='" + this.getLoggedUserName() + "')");
		//select t from Trip t where user.id=this.getUser(username).getId()
        List<Trip> result = query.getResultList();
		return result;

	}
	
	public List<User> getAllUsers(){
    	
    	Query query = entityManager.createQuery("select u from User u left join fetch u.role");
    	List<User> result=query.getResultList();
    	
		if(!result.isEmpty()){
			for(User u : result){
	    		u.setAge(DateService.getAge(u.getBirthDate()));
	    	}
		    return result;
		}else{
			return null;
		}
    }
	
}
