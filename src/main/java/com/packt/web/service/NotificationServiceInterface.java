package com.packt.web.service;

import java.util.List;

import com.packt.web.bean.Notification;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

public interface NotificationServiceInterface {

	public List<Notification> getAllNotification(User user);
	public List<Notification> getNonConfirmedNotification(User user, String type);
	public Notification findById(int id);
	public void save(Notification notification);
	public void remove(int id);//trip id
	public void remove(Notification notification);
	public void merge(Notification notification);
	public void update(Notification notification);
	public Notification findByTripAndUser(Trip trip, User traveler);
}
