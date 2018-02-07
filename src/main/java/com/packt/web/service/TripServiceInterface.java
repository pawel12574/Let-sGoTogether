package com.packt.web.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

public interface TripServiceInterface {

	
	public void addTrip(Trip trip);
		
	public void removeTrip(Trip trip);
	
    public void update(Trip trip);
	
	public List<Trip> findTrip(String a, String b);
	
	public List<Trip> findTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo);
	
	public List<Trip> findTripByCoordinatesAndDate(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo, String date);
	
	public Trip getTripDetails(int id);
	
	public List<User> getTravelers(int id);
	
	public Trip getTripByIdFetchUsers(int id);

	public List<Trip> getAllUserTrip(User user);
	
    public List<Trip> getTripUserTravel();
	
	public void addUserToTrip(Trip trip);
	
	public boolean isAvailable(Trip trip);
	
	public boolean isUserInTrip(int id); //trip.id
	
	public List<Trip> getAllTrip();
	
    public void addReservation(Reservation r);

	public void confirmReservation(Reservation r);
	
	public void removeTrip(int id, User user); 
	
	public void removeTrip(int id);

}