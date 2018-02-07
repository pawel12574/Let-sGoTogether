
package com.packt.web.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.packt.web.Dao.TripDao;
import com.packt.web.Dao.TripDaoInterface;
import com.packt.web.Dao.UserDao;
import com.packt.web.Dao.UserDaoInterface;
import com.packt.web.bean.Role;
import com.packt.web.bean.Trip;
import com.packt.web.bean.User;
import com.packt.web.bean.UserRating;
import com.packt.web.service.Email;
import com.packt.web.service.TripServiceInterface;
import com.packt.web.service.UserRatingServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class HomeController {
	
	
	@Autowired
	TripServiceInterface tripService;
	@Autowired
	UserServiceInterface userService;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String welcome() {
		/*final User existUsers = userService.getUser("lgtpb@op.pl");
		 if(existUsers==null){   
		    User user = new User();
			user.setUsername("admin@user.pl");
			user.setPassword("123456");
			user.setName("Jan");
			user.setSurname("Kowalski");
			user.setBirthDate(new Date());
			user.setPhoneNumber("222456559");

			Role role = new Role();
			role.setRole(1);
			role.setUser(user);
			user.setRole(role);
			userService.createUser(user);
		 }
		 */
		//User user = userService.getUser("pawel");
		//UserRating rate=new UserRating();
		
		/*rate.setRating(5);
		rate.setUser(user);
		userRatingService.addUserRating(rate);
		rate.setRating(6);
		rate.setUser(user);
		userRatingService.addUserRating(rate);*/
		//!!!!List<Trip> trip = userService.getMyOwnTrip();
		
		
		//Car car=new Car();
	    //car.setModel("saab93");
	 	//user.getCars().add(car);
		
		/*Trip trip=new Trip();
		trip.setFromPlace("£om¿a");
		trip.setToPlace("Bia³ystok");
		trip.setFreeSeat("4");
		
		trip.setUser(user);
		
		
		tripService.addTrip(trip);*/
	//	user.getUserTrips().add(trip);
		
	//	userDao.addUser(user);
		
		return "welcome";
		
		
		
		
		
		//tripDao.addTrip(trip);
		
	
	}
}