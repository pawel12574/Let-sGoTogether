package com.packt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.NotificationDaoInterface;
import com.packt.web.bean.Notification;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

@Service
@Transactional
public class NotifiactionService implements NotificationServiceInterface{

	@Autowired
	NotificationDaoInterface notificationDao;
	
	
	public List<Notification> getAllNotification(User user) {
		return notificationDao.getAllNotification(user);
	}
	
	public List<Notification> getNonConfirmedNotification(User user, String type){
		if(type.equals("similarTrip"))
		  return notificationDao.getNonConfirmedNotificationForSimilarTrip(user);
		else if(type.equals("tripUpdated"))
		       return notificationDao.getNonConfirmedNotificationForTripChange(user);
		     else 
		       return null;		  
		  
	}

	public Notification findById(Long id) {
		return notificationDao.findOne(id);
	}
	
	public void remove(Long id){
		notificationDao.removeByTripId(id);
	}
	
	public void remove(Notification notification){
		notificationDao.remove(notification);
	}
	
	public void save(Notification notification) {
		notificationDao.save(notification);
	}

	public void merge(Notification notification) {
		notificationDao.merge(notification);
	}
	
	public void update(Notification notification){
		notificationDao.update(notification);
	}
	
	public Notification findByTripAndUser(Trip trip, User traveler){
		return notificationDao.findByTripAndUser(trip, traveler);
	}


	

	
	
}
