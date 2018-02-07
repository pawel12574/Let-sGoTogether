package com.packt.web.Dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.User;
import com.packt.web.bean.UserRating;

@Repository
public class UserRatingDao implements UserRatingDaoInterface{
	
	@PersistenceContext
	private EntityManager entityManager;

	

	@Transactional                        
	public void addUser(UserRating userRating) {
		entityManager.persist(userRating);
	}

	@Transactional
	public void mergeUser(UserRating userRating) {
		entityManager.merge(userRating);
	}
	
	@Transactional
	public void removeUserRating(int id) {//trip.id
		Query query = entityManager.createQuery("delete UserRating where trip.id="+id+"");
		query.executeUpdate();
	}	

	@Transactional
	public String getUserRating(int userId) {
	   
		Query query = entityManager.createNativeQuery("SELECT AVG(rating) FROM `userrating` WHERE user_id="+userId+"");
        BigDecimal result= (BigDecimal) query.getSingleResult();
        if(result!=null)
		   return result.toString().substring(0, 3);
		else 
		   return "";

	}
	
	@Transactional
	public boolean isRatedByUser(int tripId, int authorId) {
	  
		Query query = entityManager.createQuery("select r from UserRating r where r.author.id="+authorId+" and r.trip.id="+tripId+"");
		List<UserRating> result = query.getResultList();
		if(result.isEmpty())
			return false;
		else 
			System.out.println(result.get(0).getId());
			return true;
		
				
    }
}