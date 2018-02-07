package com.packt.web.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.web.bean.JsonMessage;
import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;
import com.packt.web.service.ReservationServiceInterface;
import com.packt.web.service.TripServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationServiceInterface reservationService;
    @Autowired
    UserServiceInterface userService;
    @Autowired
    TripServiceInterface tripService;
	
    @RequestMapping(value="/reservation/isConfirmed/{id}", method=RequestMethod.GET)//trip.id
	public @ResponseBody JsonMessage isConfirmed(@PathVariable int id){
    	//JSONObject object = new JSONObject();
    	//  object.put("confirmed", "rajesh");
    	JsonMessage m = new JsonMessage();
		if(reservationService.isConfirmed(userService.getLoggedUser(), tripService.getTripDetails(id))){ // (user, trip)
		   m.setMessage("Potwierdzony");
		   return m;
		}
		else
		   if(tripService.isUserInTrip(id))	{
		       m.setMessage("Czekaj na potwierdzenie");
		       return m;
		       } 
		   else { m.setMessage("");
		          return m;
		          }
	}
    
    @RequestMapping(value="/reservation/add/{idTrip}", method=RequestMethod.GET)// trip.id
   	public @ResponseBody ResponseEntity<String> confirm(@PathVariable int idTrip){
   		
    	Trip t = tripService.getTripDetails(idTrip);
    	Reservation r=new Reservation();
    	r.setTraveler(userService.getLoggedUser());
	    r.setTrip(t);
		r.setConfirmed(false);
		tripService.addReservation(r);
    	
   		return  new ResponseEntity<>(HttpStatus.OK);
   	
   	}
    
    @RequestMapping(value="/reservation/isUserConfirmed/{idTrip}/{idUser}", method=RequestMethod.GET)//trip.id, user.id
	public @ResponseBody boolean isUserConfirmed(@PathVariable int idTrip, @PathVariable int idUser){
    	User u = userService.getUser(idUser);
    	Trip t = tripService.getTripDetails(idTrip);
    	return reservationService.isConfirmed(u, t);
	}
    
    @RequestMapping(value="/reservation/confirm/{idTrip}/{idUser}", method=RequestMethod.GET)// trip.id/user.id
   	public @ResponseBody ResponseEntity<String> confirm(@PathVariable int idTrip, @PathVariable int idUser){
   		
    	Trip t = tripService.getTripDetails(idTrip);
    	User u = userService.getUser(idUser);
    	reservationService.confirm(t, u);
    	
   		return  new ResponseEntity<>(HttpStatus.OK);
   	
   	}
    
    @RequestMapping(value="/reservation/remove/{idTrip}", method=RequestMethod.GET)// trip.id
   	public @ResponseBody ResponseEntity<String> delete(@PathVariable int idTrip){
   		
    	Trip t = tripService.getTripDetails(idTrip);
    	User u = userService.getLoggedUser();
    	
    	reservationService.remove(t, u);
    	
   		return  new ResponseEntity<>(HttpStatus.OK);
   	
   	}
	
}
