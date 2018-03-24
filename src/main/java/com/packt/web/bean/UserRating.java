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
public class UserRating extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Min(value = 1)
    @Max(value = 5)
    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne  //oceniany
    private User user;
    
    @ManyToOne // oceniający
    private User author;
    
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Trip trip;
	
	public UserRating(){}
    
	public UserRating(int rating, User user) {
		this.rating = rating;
		this.user = user;
	}


	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
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

}
