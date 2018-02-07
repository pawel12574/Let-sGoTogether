package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;

public interface ReservationDaoInterface {

	public void addReservation(Reservation r);
	public List<Reservation> getReservationByTripId(int id);
	public void remove(Trip t, User u);
	public void remove(Reservation r);
	public void confirm(Reservation r);
	public Reservation getReservationByUserAndTrip(User u, Trip t);
}
