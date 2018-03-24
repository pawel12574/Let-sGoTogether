package com.packt.web.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.packt.web.bean.AbstractTrip;
import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import org.springframework.stereotype.Component;

@Component
public class DateService {

	
	public static int getAge(Date date){
		
		LocalDate a=LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date));
		LocalDate b=LocalDate.now();
		return Period.between(a, b).getYears();
	}
	
	public static String parse(String time){
		SimpleDateFormat in= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = null;
		try {
			d = in.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formattedTime = out.format(d);
		return formattedTime;
		
	}
	
	public static Date getCurrentDate(){
		Date currentDate = new Date();
		return currentDate;
	}
	
	public static String getCurrentFormatedDate(){
		SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date currentDate = new Date();
		return dFormat.format(currentDate);
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd 'o' HH:mm");
		Date currentDate = date;
		return dFormat.format(currentDate);
	}
	
	public static String dateToStringv2(Date date){
		SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date currentDate = date;
		return dFormat.format(currentDate);
	}
	
	public static String dateToStringv3(Date date){
		SimpleDateFormat dFormat= new SimpleDateFormat("yyyyMMdd");
		Date currentDate = date;
		return dFormat.format(currentDate);
	}
	
	public static Date stringToDate(String date) throws ParseException{
		SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dFormat.parse(date);
	}
	
	public static String addSecondsToDate(int seconds, Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dFormat.format(calendar.getTime());
		//DateTimeFormatter dFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
	}
	
}
