package com.packt.web.service;

import java.util.List;

import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;


public interface ReservationServiceInterface {

	
	public void addReservation(Reservation r);
	public boolean isConfirmed(User u, Trip t);
	public List<Reservation> getReservationByTripId(Long id);
	public void remove(Trip t, User u);
	public void remove(Reservation r);
	public void confirm(Trip t, User u);
	
}
