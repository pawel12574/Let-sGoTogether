package com.packt.web.Dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;
import com.packt.web.service.DateService;


@Repository
@Transactional
public class TripSdDao implements TripSdDaoInterface {

	@PersistenceContext
	private EntityManager entityManager;
	private User user;
	
	public List<TripSd> getTripSd() {
		Query query=entityManager.createQuery("select ts from TripSd ts left join fetch t.travelers ");
		List<TripSd> result=query.getResultList();
		if(!result.isEmpty()){
			for(TripSd t:result){
				//t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
			}
		    return result;
		}else{
			return null;
		}
	}
	
	public TripSd getTripSdById(int id){
		Query query = entityManager.createQuery("select t from TripSd t left join fetch t.user where t.id=" + id + "");
		List<TripSd> result = query.getResultList();
		if (!result.isEmpty()) {
			for (TripSd t : result) {
			//	t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
			}
			return result.get(0);
		} else
			return null;
		
	}

    public List<TripSd> getTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, Date date, String rangeFrom, String rangeTo){
		String dateString=DateService.dateToStringv3(date);
		
		Query query=entityManager.createNativeQuery("SELECT *,(((acos(sin(("+fromLat+"*pi()/180)) * sin((`FROMLAT`*pi()/180))+cos(("+fromLat+"*pi()/180)) * cos((`FROMLAT`*pi()/180)) * cos((("+fromLng+" - `FROMLNG`)*pi()/180))))*180/pi())*60*1.1515) AS `distance`, (((acos(sin(("+toLat+"*pi()/180)) * sin((`TOLAT`*pi()/180))+cos(("+toLat+"*pi()/180)) * cos((`TOLAT`*pi()/180)) * cos((("+toLng+" - `TOLNG`)*pi()/180))))*180/pi())*60*1.1515)  AS `distance2`"
				+ " FROM `tripsd` LEFT JOIN user ON tripsd.owner_id=user.id "
				+ "where `dateBegin`<='"+dateString+"' AND `dateEnd`>='"+dateString+"' HAVING `distance` <= "+rangeFrom+" AND `distance2` <="+rangeTo+" ORDER BY `distance`", TripSd.class);
		List<TripSd> result=(List<TripSd>)query.getResultList();
		
		for(TripSd t : result){
			query=entityManager.createQuery("select t.user from TripSd t where t.id='"+t.getId()+"'");
			user=(User) query.getSingleResult();
			t.setUser(user);
		//	t.setDate(DateService.dateToString(t.getTripDate()));
			t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
		}
		
	    return result;
	}
    
    public List<TripSd> getAllUserTrip(User user) {
		Query query=entityManager.createQuery("select t from TripSd t where t.user.id="+user.getId()+"");
		List<TripSd> result=query.getResultList();
		if(!result.isEmpty()){
			for(TripSd t:result){
			//	t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
			}
		    return result;
		}else{
			return null;
		}
		
	}
    
	public List<TripSd> getAllTrip() {
		Query query=entityManager.createQuery("select t from TripSd t");
		List<TripSd> result=query.getResultList();
		if(!result.isEmpty()){
			for(TripSd t:result){
			//	t.setDate(DateService.dateToString(t.getTripDate()));
				t.setCreatedDate(DateService.dateToStringv2(t.getCreated()));
			}
		    return result;
		}else{
			return null;
		}

	}

	public void saveTripSd(TripSd trip) {
		entityManager.persist(trip);
    }

	public void mergeTripSd(TripSd trip) {
		entityManager.merge(trip);
	}
	
	public void removeTripSd(TripSd tripSd){
		entityManager.remove(tripSd);
	}

     

}
