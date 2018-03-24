package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Notification;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

public interface NotificationDaoInterface {
	
	public List<Notification> getNotificationForSimilarTrip(User user);
	public List<Notification> getNonConfirmedNotificationForSimilarTrip(User user);
	public List<Notification> getNotificationForTripChange(User user);
	public List<Notification> getNonConfirmedNotificationForTripChange(User user);
	public List<Notification> getAllNotification(User user);
	public Notification findOne(Long  id);
	public void removeByTripId(Long id);
	public void remove(Notification n);
	public void save(Notification notification);
	public void merge(Notification notification);
	public void update(Notification notification);
	public Notification findByTripAndUser(Trip trip, User traveler);
}
