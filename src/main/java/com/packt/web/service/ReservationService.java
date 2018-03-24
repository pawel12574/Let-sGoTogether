package com.packt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.web.Dao.ReservationDaoInterface;
import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

@Service
public class ReservationService implements ReservationServiceInterface {

	@Autowired
	ReservationDaoInterface reservationDao;
	
	public void addReservation(Reservation r) {
		reservationDao.addReservation(r);
	}
	
	public boolean isConfirmed(User u, Trip t){
		Reservation r = reservationDao.getReservationByUserAndTrip(u, t);
		if(r!=null){
		  if(r.isConfirmed())
			return true;
		  else 
			return false;
		}else
			return false;
	}

	public List<Reservation> getReservationByTripId(Long id){
		return reservationDao.getReservationByTripId(id);
	}
	
	public void remove(Trip t, User u){
		reservationDao.remove(t, u);
		
	}
	
	public void remove(Reservation r){
		reservationDao.remove(r);
	}
	
	public void confirm(Trip t, User u){
		Reservation r = reservationDao.getReservationByUserAndTrip(u, t);
		r.setConfirmed(true);
		reservationDao.confirm(r);
	}
	

}
