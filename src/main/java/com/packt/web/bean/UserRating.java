package com.packt.web.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "userrating")
public class UserRating implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue
	int id;
	
    @NotNull
	@Min(value = 1)
    @Max(value = 5)
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToOne  //oceniany
    private User user;
    
    @ManyToOne // oceniaj¹cy
    private User author;
    
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Trip trip;
	
	public UserRating(){}
    
	public UserRating(int id, Integer rating, User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
		UserRating other = (UserRating) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
