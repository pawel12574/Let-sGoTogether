package com.packt.web.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

@Repository
@Transactional
public class ReservationDao implements ReservationDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void addReservation(Reservation r) {
		entityManager.persist(r);
	}

	public void confirm(Reservation r) {
		entityManager.merge(r);	
	}
	
	public List<Reservation> getReservationByTripId(int id){
		Query query = entityManager.createQuery("select r from Reservation r where r.trip.id="+id+"");
		List<Reservation> result = query.getResultList();
		if(!result.isEmpty())
			return result;
		else
		    return null;
		
	}
	
	public void remove(Trip t, User u){ // trip.id
		Query query = entityManager.createQuery("delete Reservation where trip.id="+t.getId()+" and traveler.id="+u.getId()+"");
		query.executeUpdate();
	}
	
	public void remove(Reservation r){ // trip.id
		entityManager.remove(r);
	}
	
	public Reservation getReservationByUserAndTrip(User u, Trip t){
		Reservation reservation = null;
		try{
		   Query query = entityManager.createQuery("select r from Reservation r where r.trip.id="+t.getId()+" and r.traveler.id="+u.getId()+"");
		   reservation = (Reservation) query.getSingleResult();
		}
		
		catch(NoResultException e){
		  System.out.println("no result for parameter");;
		}
		
		catch(NullPointerException e){
		  System.out.println("null pointer");
		}
		return reservation;
	}

}
