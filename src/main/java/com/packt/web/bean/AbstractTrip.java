package com.packt.web.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Table(name = "Trip")
public abstract class AbstractTrip extends AbstractEntity implements Serializable{


	private static final long serialVersionUID = 1L;


	@NotEmpty
	@Column(name = "FROMLAT")
	private String fromLat;
	
	@NotEmpty
	@Column(name = "FROMLNG")
	private String fromLng;
	
	@NotEmpty
	@Column(name = "TOLAT")
	private String toLat;
	
	@NotEmpty
	@Column(name = "TOLNG")
	private String toLng;

	@NotEmpty
	@Column(name = "FROMPLACE")
	private String fromPlace;

	@NotEmpty
	@Column(name = "TOPLACE")
	private String toPlace;

	@NotNull
	@Column(name = "FREESEAT")
	private int freeSeat;

	
	@Transient
	private String createdDate; //formated created(frontend)
	
	@Column(name = "description")
	private String description;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="owner_id")
	private User user;

	public AbstractTrip() {

	}

	public AbstractTrip(String from, String to, int freeSeat, User user, String fromLat, String fromLng, String toLat, String toLng) {
		this.fromPlace = from;
		this.toPlace = to;
		this.freeSeat = freeSeat;
		this.user=user;
        this.fromLat=fromLat;
        this.fromLng=fromLng;
        this.toLat=toLat;
        this.toLng=toLng;
     }


	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	public int getFreeSeat() {
		return freeSeat;
	}

	public void setFreeSeat(int freeSeat) {
		this.freeSeat = freeSeat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFromLat() {
		return fromLat;
	}

	public void setFromLat(String fromLat) {
		this.fromLat = fromLat;
	}

	public String getFromLng() {
		return fromLng;
	}

	public void setFromLng(String fromLng) {
		this.fromLng = fromLng;
	}

	public String getToLat() {
		return toLat;
	}

	public void setToLat(String toLat) {
		this.toLat = toLat;
	}

	public String getToLng() {
		return toLng;
	}

	public void setToLng(String toLng) {
		this.toLng = toLng;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "AbstractTrip{" +
				"fromPlace='" + fromPlace + '\'' +
				", toPlace='" + toPlace + '\'' +
				", createdDate='" + createdDate + '\'' +
				'}';
	}
}
