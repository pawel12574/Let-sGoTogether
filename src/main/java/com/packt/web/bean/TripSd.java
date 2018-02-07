package com.packt.web.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tripsd")
public class TripSd extends AbstractTrip implements Serializable{

	
	private static final long serialVersionUID = 5073466606606499172L;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateBegin;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateEnd;
	
	
    public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public TripSd() {
		super();
	}

	public TripSd(String from, String to, int price, int freeSeat, User user, String fromLat, String fromLng, String toLat, String toLng) {
		super(from, to, freeSeat, user, fromLat, fromLng, toLat, toLng);
		
	}
	
}
