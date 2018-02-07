package com.packt.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.TripSdDaoInterface;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;

@Service
@Transactional
public class TripSdService implements TripSdServiceInterface {

	@Autowired
	TripSdDaoInterface tripSdDao;
	
	
	public List<TripSd> getTripSd() {
		return tripSdDao.getTripSd();
	}
    
	public List<TripSd> findTripByCoordinates(String fromLat, String fromLng, String toLat, String toLng, Date date, String rangeFrom, String rangeTo) {
		return tripSdDao.getTripByCoordinates(fromLat, fromLng, toLat, toLng, date, rangeFrom, rangeTo);
	}
	
	public List<TripSd> getAllUserTrip(User user){
		return tripSdDao.getAllUserTrip(user);
	}
	
	public void saveTripSd(TripSd trip) {
		tripSdDao.saveTripSd(trip);
		
	}

	public void update(TripSd trip) {
		tripSdDao.mergeTripSd(trip);
		
	}

	public void removeTrip(int id, User user) {
		TripSd tripSd = tripSdDao.getTripSdById(id);
		if(tripSd.getUser().getUsername().equals(user.getUsername())){
			tripSdDao.removeTripSd(tripSd);
		}
	}
	
	public List<TripSd> getAllTrip(){
		return tripSdDao.getAllTrip();
	}

}
