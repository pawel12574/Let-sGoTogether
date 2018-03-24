package com.packt.web.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "chat")
public class Chat extends AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="trip_id")
	private Trip trip;
	
	
	@OneToMany(cascade = {CascadeType.MERGE,  CascadeType.REMOVE}, orphanRemoval=true,  mappedBy="chat")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	private Set<Message> messages = new HashSet<Message>();
	
	public Chat(){}
	
	public Chat(Trip trip, Set<Message> messages) {
		this.trip = trip;
		this.messages = messages;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}


}
