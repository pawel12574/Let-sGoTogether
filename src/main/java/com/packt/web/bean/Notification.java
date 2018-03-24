package com.packt.web.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "notification")
public class Notification extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 5011410129693561802L;
	
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private Trip trip;
	
	@Column
	@Type(type="boolean")
	private boolean confirmed=false;
	
	@NotEmpty
	private String type;
	
	@ManyToOne
	@JoinColumn(name="informed_id")
	private User informed;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date created;
	
	public Notification(){}

	public Notification(Trip trip, boolean confirmed, User informed) {
		
		this.trip = trip;
		this.confirmed = confirmed;
		this.informed = informed;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public User getInformed() {
		return informed;
	}

	public void setInformed(User informed) {
		this.informed = informed;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Notification{" +
				"type='" + type + '\'' +
				", informed=" + informed +
				", created=" + created +
				'}';
	}
}
