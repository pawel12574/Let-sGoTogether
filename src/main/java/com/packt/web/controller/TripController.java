package com.packt.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.Chat;
import com.packt.web.bean.Notification;
import com.packt.web.bean.Reservation;
import com.packt.web.bean.Trip;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;
import com.packt.web.dto.TripDTO;
import com.packt.web.service.DateService;
import com.packt.web.service.NotificationServiceInterface;
import com.packt.web.service.ReservationServiceInterface;
import com.packt.web.service.TripSdServiceInterface;
import com.packt.web.service.TripServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class TripController {

	@Autowired
	TripServiceInterface tripService;
	@Autowired
	TripSdServiceInterface tripSdService;
	@Autowired
	NotificationServiceInterface notificationService;
	@Autowired
	UserServiceInterface userService;
	@Autowired
	ReservationServiceInterface reservationService;
	
	//viewResolwer
    @RequestMapping(value="/tripDetails", method=RequestMethod.GET)
	public String getTripDetailsView(){
			
		return "tripDetails";
	}
    
    @RequestMapping(value="/tripAdd", method=RequestMethod.GET)
    public String tripAddView(){
    	return "tripAdd";
    }
    
    @RequestMapping(value="/trip/get", method=RequestMethod.GET)
    public String tripsView(){
    	return "trips";
    }
	
	// REST, 
	@RequestMapping(value="/trip/find", method = RequestMethod.POST)
	public @ResponseBody List<Trip> list(@RequestBody TripDTO trip){
		
		double tempRangeFrom = Double.parseDouble(trip.getRangeFrom())/1000;
		double tempRangeTo = Double.parseDouble(trip.getRangeTo())/1000;
		trip.setRangeFrom(Double.toString(tempRangeFrom));
		trip.setRangeTo(Double.toString(tempRangeTo));
		
		List<Trip> result;
		if(trip.getDate()==""){
			result = tripService.findTripByCoordinates(trip.getFromLat(), trip.getFromLng(), trip.getToLat(), trip.getToLng(), trip.getRangeFrom(), trip.getRangeTo());
		}
		else{
			result = tripService.findTripByCoordinatesAndDate(trip.getFromLat(), trip.getFromLng(), trip.getToLat(), trip.getToLng(), trip.getRangeFrom(), trip.getRangeTo(), trip.getDate());
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/trip/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody Trip trip){
		Trip t = tripService.getTripByIdFetchUsers(trip.getId());
		
		
		if(t.getFreeSeat()>0 && tripService.isAvailable(t) && !t.getUser().getUsername().equals(userService.getLoggedUserName())) {
		   tripService.addUserToTrip(t);
         
		}
		

		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/trip/removeUser/{idTrip}", method = RequestMethod.GET)
	public ResponseEntity<String> removeUser(@PathVariable Long idTrip){
		
		Trip t = tripService.getTripByIdFetchUsers(idTrip);
		t.getTravelers().remove(userService.getLoggedUser());
		t.setFreeSeat(t.getFreeSeat()+1);
        tripService.update(t);   
		  
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/trip/details/{id}", method=RequestMethod.GET) //id Trip
	public @ResponseBody Trip trip(@PathVariable Long id){
		Trip trip=tripService.getTripDetails(id);
		if (tripService.isUserInTrip(id) )
			trip.setIsTripContainsUser(true);
		return trip;
	}
	
	@RequestMapping(value="/trip/addTrip", method = RequestMethod.POST)
	public @ResponseBody Trip trip(@RequestBody Trip trip){
	   User user = userService.getLoggedUser();
	   
		Notification note;
	    List<TripSd> similar = tripSdService.findTripByCoordinates(trip.getFromLat(), trip.getFromLng(), trip.getToLat(), trip.getToLng(), trip.getTripDate(), "1", "1");
		
	    trip.setUser(user);
		Chat chat = new Chat();
		chat.setTrip(trip);
		trip.setChat(chat);
		if(trip.getFreeSeat()==0){// if taxi, set freeSeat to 3+1(author)
			trip.setFreeSeat(3);
		    trip.setTaxiTrip(true);
		}
		trip.setDate(DateService.dateToString(trip.getTripDate())); //parse Date to String
		trip.setEndDate(DateService.addSecondsToDate(trip.getDuration(), trip.getTripDate()));//parse Date to String
		trip.setFromPlace(trip.getFromPlace().replace(", Polska", ""));
		trip.setToPlace(trip.getToPlace().replace(", Polska", ""));
		
		tripService.addTrip(trip);
		
		if(!similar.isEmpty()){
			
			for (TripSd tripSd : similar){
				if(tripSd.getFreeSeat() <= trip.getFreeSeat()){
			       note= new Notification();
			       note.setTrip(trip);
			       note.setInformed(tripSd.getUser());
			       note.setType("similarTrip");
			       if(!tripSd.getUser().getUsername().equals(user.getUsername()))
				      notificationService.save(note);
				}  
			}
		}
		
	   
	  
       return trip;
	}
	
	@RequestMapping(value="/trip/alluser", method=RequestMethod.GET)
	public @ResponseBody List<Trip> getAllUserTrip(){
		return tripService.getAllUserTrip(userService.getLoggedUser());// all trip,  user is created
		
	}
	
	@RequestMapping(value="/trip/userTravels", method=RequestMethod.GET)//get trip user is traveler
	public @ResponseBody List<Trip> getTripUserTravels(){
		return tripService.getTripUserTravel();// all trip user travel
		
	}
	
	@RequestMapping(value="/trip/isUserTraveler/{id}", method=RequestMethod.GET)
	public @ResponseBody boolean isUserTraveler(@PathVariable Long id){ // trip id
		return tripService.isUserInTrip(id);
	}
	
	@RequestMapping(value="/trip/travelers/{id}", method=RequestMethod.GET)//allow when trip author, get travelers
	public @ResponseBody List<User> getTravelers(@PathVariable Long id){
		Trip trip=tripService.getTripDetails(id);
		List<User> list = null;
		try{
		  if(trip.getUser().getUsername().equals(userService.getLoggedUserName())){
		    list = tripService.getTravelers(id);
			for(User u:list){
				u.setConfirmedTraveler(reservationService.isConfirmed(u, trip));
			}
			return list;
		  }
		}
		catch(NullPointerException ex){
			System.out.println("trip entity is null");
		}
		 
		return list;
	}

	@RequestMapping(value="/trip/all", method=RequestMethod.GET)// admin only
	public @ResponseBody List<Trip> getAllTrip(){
		return tripService.getAllTrip();
		
	}
	
	@RequestMapping(value="/trip/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable Long id){
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().size()>1)//if list of priviliges>1(ROLE_ADMIN)
			tripService.removeTrip(id);
		else
		    tripService.removeTrip(id, userService.getLoggedUser());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/trip/update", method = RequestMethod.POST)
	public ResponseEntity<String> updateTrip(@RequestBody Trip trip){
		Notification note;
		Notification oldNote;
		User user = userService.getLoggedUser();
		
		Trip t = tripService.getTripDetails(trip.getId());
		t.setDate(DateService.dateToString(trip.getTripDate())); //parse Date to String
		t.setTripDate(trip.getTripDate());
		t.setEndDate(DateService.addSecondsToDate(trip.getDuration(), trip.getTripDate()));//parse Date to String, calculate tripDate+duration
		t.setPrice(trip.getPrice());
		t.setFromPlace(trip.getFromPlace());
		t.setToPlace(trip.getToPlace());
		t.setDescription(trip.getDescription());
		t.setTaxiTrip(trip.isTaxiTrip());
		
		List<User> travelers = tripService.getTravelers(trip.getId());
        if(!travelers.isEmpty()){  //notify user about changes
			
			for (User traveler : travelers){
				oldNote = notificationService.findByTripAndUser(trip, traveler);
				if(oldNote.getType().equals("tripUpdated")){
				  oldNote.setConfirmed(false);
			      notificationService.update(oldNote);
				}else{
				  note= new Notification();
				  note.setTrip(trip);
				  note.setInformed(traveler);
				  note.setType("tripUpdated");
				  notificationService.save(note);
				}
				
				
			}
		}
		if(t.getUser().getUsername().equals(user.getUsername()) || user.getRole().getRole()==1){
		  tripService.update(t);
		}  
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
