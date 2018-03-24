package com.packt.web.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Message;
import com.packt.web.bean.Taxi;
import com.packt.web.service.DateService;

@Repository
@Transactional
public class TaxiDao implements TaxiDaoInterface{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void persist(Taxi taxi) {
		entityManager.persist(taxi);
		
	}

	public List<Taxi> getAll() {
		Query query=entityManager.createQuery("select t from Taxi t");
		List<Taxi> result = query.getResultList();
		if(!result.isEmpty()){
			for(Taxi t:result){
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
		    }
		return result;
		}
		else 
		   return null;
	}

	public void remove(Long id) {
		Query query=entityManager.createQuery("delete from Taxi t where t.id="+id+"");
		query.executeUpdate();
	}

	public void update(Taxi taxi) {
		entityManager.merge(taxi);
		
	}

	public Taxi getById(Long id) {
		Query query=entityManager.createQuery("select t from Taxi t where t.id="+id+"");
		Taxi taxi = (Taxi)query.getSingleResult();
		if(taxi!=null){
			taxi.setCreatedDate(DateService.dateToStringv2(taxi.getCreated()));
		    return taxi;
		}
		else
			return null;
	}

}
