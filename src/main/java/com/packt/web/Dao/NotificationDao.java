package com.packt.web.Dao;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Expression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Notification;
import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;
import com.packt.web.service.DateService;

@Repository
@Transactional
public class NotificationDao implements NotificationDaoInterface{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<Notification> getNotificationForSimilarTrip(User user) {
		  LocalDate date = LocalDate.now().minusDays( 30 ); // global variable, check if initialize once
	      Query query=entityManager.createQuery("select n from Notification n left join fetch n.informed where n.informed.id='"+user.getId()+"' and n.type='similarTrip' and n.created>'"+date+"'");
		  List<Notification> result=query.getResultList();
		  if(!result.isEmpty()){
			  for(Notification n : result){
				  n.getTrip().setDate(DateService.dateToString(n.getTrip().getTripDate()));
			  }
		  }
		 return result;
		
	}
	
	public List<Notification> getNonConfirmedNotificationForSimilarTrip(User user) {
		  LocalDate date = LocalDate.now().minusDays( 30 );
	      Query query=entityManager.createQuery("select n from Notification n left join fetch n.informed where n.confirmed='false' and n.informed.id='"+user.getId()+"' and n.type='similarTrip' and n.created>'"+date+"'");
		  List<Notification> result=query.getResultList();
		  return result;
		
	}
	
	public List<Notification> getNotificationForTripChange(User user) {
		  LocalDate date = LocalDate.now().minusDays( 30 );
	      Query query=entityManager.createQuery("select n from Notification n left join fetch n.informed where n.informed.id='"+user.getId()+"' and n.type='tripUpdated' and n.created>'"+date+"'");
		  List<Notification> result=query.getResultList();
		  if(!result.isEmpty()){
			  for(Notification n : result){
				  n.getTrip().setDate(DateService.dateToString(n.getTrip().getTripDate()));
			  }
		  }
		  return result;
		
	}
	
	public List<Notification> getAllNotification(User user) {
		LocalDate date = LocalDate.now().minusDays( 30 );
	      Query query=entityManager.createQuery("select n from Notification n left join fetch n.informed where n.informed.id='"+user.getId()+"' and n.created>'"+date+"'");
		  List<Notification> result=query.getResultList();
		  if(!result.isEmpty()){
			  for(Notification n : result){
				  n.getTrip().setDate(DateService.dateToString(n.getTrip().getTripDate()));
			  }
		  }
		  return result;
		
	}
	
	public List<Notification> getNonConfirmedNotificationForTripChange(User user) {
		  LocalDate date = LocalDate.now().minusDays( 30 );
	      Query query=entityManager.createQuery("select n from Notification n left join fetch n.informed where n.confirmed='false' and n.informed.id='"+user.getId()+"' and n.type='tripUpdated' and n.created>'"+date+"'");
		  List<Notification> result=query.getResultList();
		  return result;
		
	}
	
	public Notification findOne(int id) {
		  Query query=entityManager.createQuery("select n from Notification n left join fetch n.informed where n.id='"+id+"'");
		  Notification notification = (Notification) query.getSingleResult();
		  if(notification!=null){
		     return notification;
		  } else
			 return null;
	}
	
	public Notification findByTripAndUser(Trip trip, User traveler){
		Query query = entityManager.createQuery("select n from Notification n left join fetch n.informed where n.trip.id="+trip.getId()+" and n.informed.id="+traveler.getId()+"");
		try{
		   Notification notification = (Notification) query.getSingleResult();
		   return notification;
		  
		   }
		catch(NoResultException e){
			Notification n = new Notification();
			n.setType("nonetype");
			return n;}
		 
		
	}
	
    public void save(Notification notification) {
		  entityManager.persist(notification);
		
	}
    
    public void removeByTripId(int id) {
		  Query query = entityManager.createQuery("delete Notification where trip.id="+id+"");
		  query.executeUpdate();
		
	}
    
    public void remove(Notification n){
    	  
    	  entityManager.remove(entityManager.contains(n) ? n : entityManager.merge(n));
    }
	
	public void merge(Notification notification) {
		  Query query=entityManager.createNativeQuery("update `notification` set `confirmed`=1 where id="+notification.getId()+"");
		  query.executeUpdate();
		
	}
	
	public void update(Notification notification) {
		  entityManager.merge(notification);
		  
		
	}

}
