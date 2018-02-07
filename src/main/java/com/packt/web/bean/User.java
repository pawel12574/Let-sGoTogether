package com.packt.web.bean;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="user")
public class User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue
	 private int id;
	 
	 @NotEmpty
	 @Column(name="USERNAME", unique=true)
	 private String username;// email(unique)
	 
	 @NotEmpty
	 @Size(min = 6)
	 @Column(name="PASSWORD")
	 private String password;
	 
	 @NotEmpty
	 @Column(name="NAME")
	 private String name;
	 
     @NotEmpty
	 @Column(name="SURNAME")
	 private String surname;
	 
   
     @Transient
     private int age;
     
     @Transient
     private boolean isConfirmedTraveler;
     
     @NotNull
     @DateTimeFormat(pattern = "yyyy-MM-dd")
     private Date birthDate;
     
     @CreationTimestamp
 	 @Temporal(TemporalType.TIMESTAMP)
 	 @JsonFormat(pattern="yyyy-MM-dd")
     private Date created;
	
     @NotEmpty
	 @Column(name="PHONENUMBER")
	 private String phoneNumber;
	 
     private boolean accountNonLocked=true;
    
     @Lob
     private byte[] avatar;
     
     @OneToOne(mappedBy="user", cascade={CascadeType.ALL})
     private Role role;
  /* @OneToOne(cascade=CascadeType.ALL)
	 @JoinTable(name="user_roles",
	            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
	     inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	     )
     private Role role;
	  
	  */
     @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})	
     private PasswordToken token; //get set
	
	 @NotFound(action = NotFoundAction.IGNORE)
	 @JsonIgnore
	 @ManyToMany(mappedBy="travelers")
	 private Set<Trip> trip=new HashSet<Trip>();
	
	 @NotFound(action = NotFoundAction.IGNORE)
	 @JsonIgnore
	 @OneToMany(mappedBy="user")
	 private Set<Trip> myTrip=new HashSet<Trip>();
	 
	 @NotFound(action = NotFoundAction.IGNORE)
	 @JsonIgnore
	 @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
	 private Set<Message> messages=new HashSet<Message>();
	 

	public User(){
		
       	}
	
	public User(String name, String username, String password, String surname, Date birthDate, int age, String phoneNumber) {
		this.name = name;
		this.username = username;
		this.password=password;
		this.surname = surname;
		this.birthDate=birthDate;
		this.age=age;
		this.phoneNumber = phoneNumber;
		}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	@JsonIgnore     //secure for return password in json
	public String getPassword() {
		return password;
	}
	@JsonProperty   //allow for send this in json
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	public boolean isAccountNonLocked() {
 		return accountNonLocked;
 	}

 	public void setAccountNonLocked(boolean accountNonLocked) {
 		this.accountNonLocked = accountNonLocked;
 	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
    public Set<Trip> getTrip() {
		return trip;
	}

	public void setTrip(Set<Trip> trip) {
		this.trip = trip;
	}
	
    public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<Trip> getMyTrip() {
		return myTrip;
	}

	public void setMyTrip(Set<Trip> myTrip) {
		this.myTrip = myTrip;
	}
    
	public boolean isConfirmedTraveler() {
		return isConfirmedTraveler;
	}

	public void setConfirmedTraveler(boolean isConfirmedTraveler) {
		this.isConfirmedTraveler = isConfirmedTraveler;
	}

	public PasswordToken getToken() {
		return token;
	}

	public void setToken(PasswordToken token) {
		this.token = token;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
