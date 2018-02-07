/*package com.packt.web.bean;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.*;



@Proxy(lazy=false)
@Entity
@Table(name="car")
public class Car {

	 @Id
	 @GeneratedValue
	 @Column(name="IDCAR")
	 private int id;
	 
	 @NotEmpty
	 @Column(name="MODEL")
	 @Size(min=3, max=20)
	 private String model;
	 
     @Column(name="AIRCONDITIONING")
	 private boolean airConditioning;
	 
     @Column(name="COUNTOFSEAT")
     private int countOfSeat;
     
     @ManyToOne
     @NotFound(action = NotFoundAction.IGNORE)
     private User carOwner;
	
     public User getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(User carOwner) {
		this.carOwner = carOwner;
	}

	public boolean isAirConditioning() {
		return airConditioning;
	}

	public Car(){}  
     
	 public Car(String model, boolean airConditioning, int countOfSeat) {
			this.model = model;
			this.airConditioning = airConditioning;
			this.countOfSeat = countOfSeat;
		}
	 
	
	 
	 
	 public int getId() {
		return id;
	}
	
	 public void setId(int id) {
		this.id = id;
	}
	
	 public String getModel() {
		return model;
	}
	
	 public void setModel(String model) {
		this.model = model;
	}
	

     public void setAirConditioning(boolean b){
	    this.airConditioning=b;
	}
	
     public int getCountOfSeat() {
		return countOfSeat;
	}
	
     public void setCountOfSeat(int countOfSeat) {
		this.countOfSeat = countOfSeat;
	}
     
     
     @Override
 	public int hashCode() {
 		return this.id;
 	}

 	@Override
 	public boolean equals(Object obj) {
 		if (obj == null) return false;
 		if (!(obj instanceof Car)) return false;
 		
 		Car other=(Car) obj;
 		return this.id == other.id;
 	}
	
}
*/