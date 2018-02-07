package com.packt.web.dto;

public class TripDTO {
	private String from = "";
	private String to = "";
	private String fromLat = "";
    private String fromLng = "";
	private String toLat = "";
	private String toLng = "";
	private String rangeFrom = "";
	private String rangeTo = "";
	private String date = "";

	public TripDTO(){}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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

	public String getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(String rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public String getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(String rangeTo) {
		this.rangeTo = rangeTo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
    
}
