package com.packt.web.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trip")
public class Trip extends AbstractTrip implements Serializable{

	
	private static final long serialVersionUID = 5181470557506620481L;
	
	@Transient
	private boolean isTripContainsUser;
	
	@NotNull
	private int duration;
	
	@NotNull
    private int distance;
    
	@NotNull
    @Column(name = "DATE", nullable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
   	private Date tripDate;//start date(backend)
   	
   	@Transient
   	private String date;//start date formated(frontend)
    
    @NotEmpty
    private String endDate;//calculated tripDate+duration;
    
    private boolean isTaxiTrip;
	
    private String price;
	
    private String taxiPrice;
	
	@NotEmpty
	private String time;
	
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToMany	@JoinTable(name = "user_trip", joinColumns = { @JoinColumn(name = "trip_id") }, inverseJoinColumns = {
			    @JoinColumn(name = "user_id") })
	private Set<User> travelers = new HashSet<User>();

	@JsonIgnore
	@OneToOne(mappedBy="trip", cascade = {CascadeType.ALL}, orphanRemoval=true)
	@NotFound(action = NotFoundAction.IGNORE)
	private Chat chat;
	
	/*@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private Taxi taxi;*/
		


	public Trip() {
		super();
		
	}

	public Trip(String from, String to, int price, int freeSeat, Date tripDate, User user, String fromLat, String fromLng, String toLat, String toLng) {
		super(from, to, freeSeat, user, fromLat, fromLng, toLat, toLng);
		this.tripDate=tripDate;
		
	}
	
	
	public Set<User> getTravelers() {
		return travelers;
	}

	public void setTravelers(Set<User> travelers) {
		this.travelers = travelers;
	}
	
	public boolean isTripContainsUser() {
		return isTripContainsUser;
	}

	public void setIsTripContainsUser(boolean isTripContainsUser) {
		this.isTripContainsUser = isTripContainsUser;
	}
	
	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	} 
	
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isTaxiTrip() {
		return isTaxiTrip;
	}

	public void setTaxiTrip(boolean isTaxiTrip) {
		this.isTaxiTrip = isTaxiTrip;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTaxiPrice() {
		return taxiPrice;
	}

	public void setTaxiPrice(String taxiPrice) {
		this.taxiPrice = taxiPrice;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

}
