package com.packt.web.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Role;

@Repository
public class RoleDao implements RoleDaoInterface{

	@PersistenceContext
	 private EntityManager entityManager;
	     
	  @Transactional  
	  public Role getRole(int id) {
	        
		  Role role = entityManager.find(Role.class, id);
		  return role;
	    }
	 

}
