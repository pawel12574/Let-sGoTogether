package com.packt.web.Dao;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Chat;
import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;
import com.packt.web.service.ChatServiceInterface;
import com.packt.web.service.DateService;
import com.packt.web.service.MessageServiceInterface;
import com.packt.web.service.NotificationServiceInterface;
import com.packt.web.service.ReservationServiceInterface;
import com.packt.web.service.UserRatingServiceInterface;
import com.packt.web.service.UserService;
import com.packt.web.service.UserServiceInterface;


@Repository
public class TripDao implements TripDaoInterface{

	@PersistenceContext
    private EntityManager entityManager;

	private User user;
	@Autowired
	UserServiceInterface userService;
	@Autowired
	ChatServiceInterface chatService;
	@Autowired
	MessageServiceInterface messageService;
	@Autowired
	UserRatingServiceInterface userRatingService;
	@Autowired
	ReservationServiceInterface reservationService;
	@Autowired
	NotificationServiceInterface notificationService;

	//dodanie przejazdu
	@Transactional
	public void addTrip(Trip trip) {
		entityManager.persist(trip);
		
	}
	//usuniecie przejazdu
	@Transactional
	public void removeTrip(Trip trip) {
		entityManager.remove(trip);
	}
	
	@Transactional
	public void removeTrip(Long id){
		
		Trip t= this.getTripById(id);
		
		//remove reservation
		/*List<Reservation> reservationList = reservationService.getReservationByTripId(id);
		if(reservationList!=null) 
			for(Reservation r: reservationList){
			  reservationService.remove(r);
			}*/
		Query query = entityManager.createQuery("delete Reservation where trip.id="+id+"");
		query.executeUpdate();
		userRatingService.removeUserRating(id);// where trip.id
		
		query = entityManager.createQuery("select c.id from Chat c where trip.id='"+id+"'");
		Long idChat = (Long)query.getResultList().get(0);
	    messageService.removeMessageByChatId(idChat);
		chatService.remove(idChat);
		notificationService.remove(id);
		
		entityManager.remove(t);
		
		
		
		//Query query=entityManager.createQuery("delete from Chat c where c.trip.id="+id+"");
		//query.executeUpdate();
		//Query query=entityManager.createQuery("delete from Trip t where t.id="+id+"");
		//query.executeUpdate();
	}
	//aktualizacja przejazdu
	@Transactional
	public void mergeTrip(Trip trip) {
		entityManager.merge(trip);
		
	}
   
	
	@Transactional
	public List<Trip> getTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo){
		
		Query query=entityManager.createNativeQuery("SELECT *,(((acos(sin(("+fromLat+"*pi()/180)) * sin((`FROMLAT`*pi()/180))+"
				+ "cos(("+fromLat+"*pi()/180)) * cos((`FROMLAT`*pi()/180)) * cos((("+fromLng+" - `FROMLNG`)*pi()/180))))*180/pi())"
						+ "*60*1.1515*1.609344) AS `distance`, (((acos(sin(("+toLat+"*pi()/180)) * sin((`TOLAT`*pi()/180))+"
								+ "cos(("+toLat+"*pi()/180)) * cos((`TOLAT`*pi()/180)) * cos((("+toLng+" - `TOLNG`)*pi()/180))))"
										+ "*180/pi())*60*1.1515*1.609344)  AS `distance2` FROM `trip` where "
										+ "`DATE`>'"+DateService.getCurrentFormatedDate()+"' AND `FREESEAT`>0 "
												+ "HAVING `distance` <= "+rangeFrom+" AND `distance2` <="+rangeTo+" "
														+ "ORDER BY `DATE`", Trip.class);
		List<Trip> result=(List<Trip>)query.getResultList();
		
		for(Trip t : result){
			query=entityManager.createQuery("select t.user from Trip t where t.id='"+t.getId()+"'");
			user=(User) query.getSingleResult();
			user.setAge(DateService.getAge(t.getUser().getBirthDate()));
			t.setUser(user);
			t.setDate(DateService.dateToString(t.getTripDate()));
			t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
		}
		
	    return result;
	}
	
	@Transactional
	public List<Trip> getTripByCoordinatesAndDate(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo, String date){
		
		Query query=entityManager.createNativeQuery("SELECT *,(((acos(sin(("+fromLat+"*pi()/180)) * sin((`FROMLAT`*pi()/180))+cos(("+fromLat+"*pi()/180)) * cos((`FROMLAT`*pi()/180)) * cos((("+fromLng+" - `FROMLNG`)*pi()/180))))*180/pi())*60*1.1515*1.609344) AS `distance`,"
				+ " (((acos(sin(("+toLat+"*pi()/180)) * sin((`TOLAT`*pi()/180))+cos(("+toLat+"*pi()/180)) * cos((`TOLAT`*pi()/180)) * cos((("+toLng+" - `TOLNG`)*pi()/180))))*180/pi())*60*1.1515*1.609344)  AS `distance2` FROM `trip"
						+ "` where `DATE` like '"+date+"%' AND `FREESEAT`>0 HAVING `distance` <= "+rangeFrom+" AND `distance2` <="+rangeTo+" ORDER BY `DATE`", Trip.class);
		List<Trip> result=(List<Trip>)query.getResultList();
		
		for(Trip t : result){
			query=entityManager.createQuery("select t.user from Trip t where t.id='"+t.getId()+"'");
			user=(User) query.getSingleResult();
			user.setAge(DateService.getAge(t.getUser().getBirthDate()));
			t.setUser(user);
			t.setDate(DateService.dateToString(t.getTripDate()));
			t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
		}
		
	    return result;
	}
	
	@Transactional
	public Trip getTripById(Long id) {
		Trip  trip=null;
		Query query=entityManager.createQuery("select t from Trip t where id="+id+"");
		try{
			trip=(Trip) query.getSingleResult();
			trip.setDate(DateService.dateToString(trip.getTripDate()));
			trip.setCreatedDate(DateService.dateToStringv2(trip.getCreated()));
		}
		catch(NoResultException ex){
			System.out.println("no result fo parameter");
		}
		
		//trip.getUser().setUsername("xd");
		return trip;
	}
	
    @Transactional
	public Trip getTripByIdFetchUsers(Long id) {
		Query query=entityManager.createQuery("select t from Trip t left join fetch t.travelers left join fetch t.user where t.id='"+id+"'");
		List<Trip> result=query.getResultList();
		if(!result.isEmpty()){
			for(Trip t:result){
				t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
				
			}
		    return result.get(0);
		}else{
			return null;
		}
	}
	
	@Transactional
	public boolean isAvaiable(Long id){
        Query query=entityManager.createQuery("select t from Trip t where t.id='"+id+"' and t.tripDate>'"+DateService.getCurrentFormatedDate()+"'");

		List<Trip> result=query.getResultList();
		if(!result.isEmpty())
			return true;
		else 
			return false;
	}
	
	@Transactional
	public List<User> getUsersInTrip(Long id){
		
		List<User> result = null;
		try{
		   Query query=entityManager.createQuery("select t.travelers from Trip t where t.id='"+id+"'");
		   result=query.getResultList();
		}
		catch(NullPointerException ex){
			System.out.println("invalid parameter");
		}
		return result;
	}
	
	@Transactional
	public List<Trip> getAllUserTrip(User user) {
		LocalDate date = LocalDate.now().minusDays( 30 );
		Query query=entityManager.createQuery("select t from Trip t where t.user.id="+user.getId()+" and t.endDate>'"+date+"'");
		List<Trip> result=query.getResultList();
		if(!result.isEmpty()){
			for(Trip t:result){
				t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
			}
		    return result;
		}else{
			return null;
		}
	}
	
	@Transactional
	public List<Trip> getTripUserTravel() {
		LocalDate date = LocalDate.now().minusDays( 30 );
		Date d=java.sql.Date.valueOf(date);
		
		String username = userService.getLoggedUserName();
        Query query = entityManager.createQuery("select distinct u.trip from User u left join u.trip where u.username='"+username+"'");
		List<Trip> result = query.getResultList();
		List<Trip> toRemove = new ArrayList<>();
		if(!result.isEmpty()){
			for(Trip t:result){
				t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
				try {
					if(DateService.stringToDate(t.getEndDate()).before(d)){
						toRemove.add(t);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			result.removeAll(toRemove);
			return result;
		}else{
			return null;
		}
	}

	@Transactional
	public List<Trip> getAllTrip() {
		Query query=entityManager.createQuery("select t from Trip t");
		List<Trip> result=query.getResultList();
		if(!result.isEmpty()){
			for(Trip t:result){
				t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
			}
			return result;
		}else{
			return null;
		}
	}
	
	  //pobranie listy przejazdow z punktu A do B
		@Transactional
		public List<Trip> getTripByFromTo(String a, String b) {
			Query query=entityManager.createQuery("select t from Trip t left join fetch t.user WHERE t.fromPlace='"+a+"' AND t.toPlace='"+b+"'");
			List<Trip> result=query.getResultList();
			return result;
		}

}
