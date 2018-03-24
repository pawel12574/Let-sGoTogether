package com.packt.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;

@Service
public interface TripSdServiceInterface {
	
	List<TripSd> getTripSd();
	public List<TripSd> findTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, Date date, String rangeFrom, String rangeTo);
	public List<TripSd> getAllUserTrip(User user);
	public void saveTripSd(TripSd trip) ;
	public void update(TripSd trip);
	public void removeTrip(Long id, User user);
	public List<TripSd> getAllTrip(); 
}
