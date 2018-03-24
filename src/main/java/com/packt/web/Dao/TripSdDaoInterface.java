package com.packt.web.Dao;

import java.util.Date;
import java.util.List;

import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;


public interface TripSdDaoInterface {
	
	public List<TripSd> getTripSd();
	public TripSd getTripSdById(Long id);
	public List<TripSd> getTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, Date date, String rangeFrom, String rangeTo);
	public List<TripSd> getAllUserTrip(User user);
	public void saveTripSd(TripSd trip);
	public void mergeTripSd(TripSd trip);
	public void removeTripSd(TripSd tripSd);
	public List<TripSd> getAllTrip(); 
	
}
