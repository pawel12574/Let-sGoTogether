package com.packt.web.Dao;

import java.text.ParseException;
import java.util.List;

import com.packt.web.bean.Trip;
import com.packt.web.bean.User;


public interface TripDaoInterface {

	public void addTrip(Trip trip);
	public void removeTrip(Trip trip);
	public void removeTrip(int id);
	public void mergeTrip(Trip trip);
	public List<User> getUsersInTrip(int id);
	public List<Trip> getAllUserTrip(User user);
	public List<Trip> getTripUserTravel();
	public List<Trip> getTripByFromTo(String a, String b);
	public List<Trip> getTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo);
	public List<Trip> getTripByCoordinatesAndDate(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo, String date);
	public List<Trip> getAllTrip();
	public Trip getTripById(int id);
	public Trip getTripByIdFetchUsers(int id);
	public boolean isAvaiable(int id);
	
}
