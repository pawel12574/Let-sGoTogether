package com.packt.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;
import com.packt.web.bean.UserRating;
import com.packt.web.dto.UserRatingDto;
import com.packt.web.service.DateService;
import com.packt.web.service.TripServiceInterface;
import com.packt.web.service.UserRatingServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class UserRatingController {

	@Autowired
	UserRatingServiceInterface userRatingService;
	@Autowired
	TripServiceInterface tripService;
	@Autowired
	UserServiceInterface userService;
	
	
	@RequestMapping(value="/rating/user/add", method=RequestMethod.POST)
	public @ResponseBody UserRatingDto rating(@RequestBody UserRatingDto rate) throws ParseException{
		// check if user is travaler
		Trip trip = tripService.getTripByIdFetchUsers(rate.getTripId());
		User user = userService.getLoggedUser();
		Date endDate = DateService.stringToDate(trip.getEndDate());
		Date now = DateService.getCurrentDate();
		
		if (trip.getTravelers().contains(user) && now.after(endDate)) {
            UserRating rating = new UserRating();
			rating.setRating(rate.getRate());
			rating.setUser(trip.getUser());
			rating.setTrip(trip);
			rating.setAuthor(userService.getLoggedUser());
			userRatingService.addUserRating(rating);
			return rate;
		}else
			return null;
	}
	
	@RequestMapping(value="/rating/user/{id}", method=RequestMethod.GET)
    public @ResponseBody String getAvgRating(@PathVariable int id){//user.id
		
		return userRatingService.getUserRating(id);
		
	}
	
	@RequestMapping(value="/rating/isAvaiable/{id}", method=RequestMethod.GET)
	public @ResponseBody boolean isRatingAvaiableforTrip(@PathVariable int id) throws ParseException{  //trip.id
		Trip trip = tripService.getTripByIdFetchUsers(id);
		User user = userService.getLoggedUser();
		Date endDate = DateService.stringToDate(trip.getEndDate());
		Date now = DateService.getCurrentDate();
		
		if (trip.getTravelers().contains(user) && now.after(endDate) && !userRatingService.isRatedByUser(trip.getId())) {
           return true;
		}else
		   return false;
	
	}
}
