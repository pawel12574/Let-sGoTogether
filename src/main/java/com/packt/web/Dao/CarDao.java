/*package com.packt.web.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Car;

@Repository
public class CarDao implements CarDaoInterface {
 
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public void addCar(Car car){
		entityManager.persist(car);
	}
	
	@Transactional
	public void removeCar(Car car){
		entityManager.remove(car);
	}
	
	@Transactional
    public void mergeCar(Car car){
    	entityManager.merge(car);
    }
    
    

}

*/