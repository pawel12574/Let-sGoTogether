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
public class Chat implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

}
