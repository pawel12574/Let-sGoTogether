package com.packt.web.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.ReservationDaoInterface;
import com.packt.web.Dao.TripDao;
import com.packt.web.Dao.TripDaoInterface;
import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;

@Service
@Transactional
public class TripService implements TripServiceInterface {

	@Autowired 
	TripDaoInterface tripDao;
	@Autowired
	UserServiceInterface userService;
	@Autowired
	ReservationDaoInterface reservationDao;
	
	public void addTrip(Trip trip){
		tripDao.addTrip(trip);
	}
	
	public void removeTrip(Trip trip){
		tripDao.removeTrip(trip);
	}
	
	public void update(Trip trip){
		tripDao.mergeTrip(trip);
	}
	
	public List<Trip> findTrip(String a, String b){
		
		List<Trip> result=tripDao.getTripByFromTo(a, b);
		return result;
	}
	
    public List<Trip> findTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo) {
		
		List <Trip> result = tripDao.getTripByCoordinates(fromLat, fromLng, toLat, toLng, rangeFrom, rangeTo);
		return result;
	}
	
    public List<Trip> findTripByCoordinatesAndDate(String fromLat, String fromLng, String toLat, String toLng, String rangeFrom, String rangeTo, String date) {
		
		List <Trip> result = tripDao.getTripByCoordinatesAndDate(fromLat, fromLng, toLat, toLng, rangeFrom, rangeTo, date);
		return result;
	}

	public Trip getTripDetails(int id) {
		Trip trip=tripDao.getTripById(id);
		return trip;
	}
	
	public List<Trip> getAllUserTrip(User user) {
		List<Trip> result=tripDao.getAllUserTrip(user);
		return result;
	}
	
    public List<Trip> getTripUserTravel(){
		return tripDao.getTripUserTravel();
		
	}

	public void addUserToTrip(Trip trip) {
		User user=userService.getLoggedUser();
		trip.getTravelers().add(user);
		trip.setFreeSeat(trip.getFreeSeat()-1);
		tripDao.mergeTrip(trip);
		
	}

	public boolean isAvailable(Trip trip) {
		
		return tripDao.isAvaiable(trip.getId());
	}

	public boolean isUserInTrip(int id) {
	  
		User user=userService.getLoggedUser();
		if(tripDao.getUsersInTrip(id).contains(user)){
			return true;
		}
		else
		return false;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Trip> getAllTrip() {
		return tripDao.getAllTrip();
	}
	
	public Trip getTripByIdFetchUsers(int id){
		return tripDao.getTripByIdFetchUsers(id);
	}
	
	public List<User> getTravelers(int id){
		return tripDao.getUsersInTrip(id);
	}
	
	public void removeTrip(int id, User user) {
		Trip trip = tripDao.getTripById(id);
		if(trip.getUser().getUsername().equals(user.getUsername())){
			tripDao.removeTrip(trip);
		}
	}
	
	public void removeTrip(int id) {
		tripDao.removeTrip(id);
	}

	public void addReservation(Reservation r) {
		reservationDao.addReservation(r);
		
	}

	public void confirmReservation(Reservation r) {
		reservationDao.confirm(r);
		
	}


}
