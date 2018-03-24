package com.packt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.AbstractTrip;
import com.packt.web.bean.Chat;
import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import com.packt.web.service.TripSdServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class TripSdController {

	
	@Autowired
	UserServiceInterface userService;
	@Autowired
	TripSdServiceInterface tripSdService;
	
	//viewResolwer
	@RequestMapping(value="tripSd/add", method=RequestMethod.GET)
	public String tripSdView(){
			
		return "tripSd";
	}
	
	@RequestMapping(value="tripsd/all", method=RequestMethod.GET)
	public String tripSdAllView(){
			
		return "tripsdall";
	}
	
	@RequestMapping(value="/tripSd/addTrip", method = RequestMethod.POST)
	public @ResponseBody TripSd trip(@RequestBody TripSd trip){
		
		trip.setUser(userService.getLoggedUser());
		tripSdService.saveTripSd(trip);
		return trip;
	}
	
	@RequestMapping(value="/tripsd/alluser", method=RequestMethod.GET)
	public @ResponseBody List<TripSd> getAllSd(){
		return tripSdService.getAllUserTrip(userService.getLoggedUser());
		
	}
	
	@RequestMapping(value="/tripSd/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable Long id){
		
		tripSdService.removeTrip(id, userService.getLoggedUser());//id tripSD, logged user object
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/tripssd/all", method=RequestMethod.GET)
	public @ResponseBody List<TripSd> getAllTripSd(){
		
		return tripSdService.getAllTrip();
	}
	
	@RequestMapping(value="/tripSd/update", method = RequestMethod.POST)
	public ResponseEntity<String> updateTrip(@RequestBody TripSd trip){
		tripSdService.update(trip);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
