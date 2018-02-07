package com.packt.web.Dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.PasswordToken;
import com.packt.web.bean.Trip;
import com.packt.web.service.DateService;

@Transactional
@Repository
public class PasswordTokenDao implements PasswordTokenDaoInterface {

	@PersistenceContext
	EntityManager entityManager;
	
	public void save(PasswordToken passwordToken) {
		//entityManager.merge(passwordToken.getUser());
		entityManager.persist(passwordToken);
		
	}
	
	public PasswordToken getToken(String token){
		Query query=entityManager.createQuery("select p from PasswordToken p left join fetch p.user where token='"+token+"'");
	   
		try{
			PasswordToken passwordToken=(PasswordToken) query.getSingleResult();
			return passwordToken;
		}
		catch(Exception ex){
			System.out.println("no result fo parameter");
			return null;
		}
		
	}

	
	public void remove(PasswordToken passToken) {
		
		PasswordToken p = entityManager.merge(passToken);
		entityManager.remove(p);
		
	}
}
	


