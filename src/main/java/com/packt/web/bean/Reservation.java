package com.packt.web.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation extends AbstractEntity{

	
	@OneToOne
	private Trip trip;
	
	@OneToOne
	private User traveler;
	
	private boolean isConfirmed;

	
	public Reservation() {
		super();
		
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public User getTraveler() {
		return traveler;
	}

	public void setTraveler(User traveler) {
		this.traveler = traveler;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	
	

}
