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
import com.packt.web.bean.Notification;
import com.packt.web.bean.TripSd;
import com.packt.web.bean.User;
import com.packt.web.service.NotificationServiceInterface;
import com.packt.web.service.TripSdServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class NotificationController {

	
	@Autowired
	NotificationServiceInterface notificationService;
	@Autowired
	UserServiceInterface userService;
	
	
	@RequestMapping(value="/notification/get", method=RequestMethod.GET)
	public String getNoticationView(){
		return "notification";
	}
	
	
	@RequestMapping(value="/notification/getNConfirmedNotification/{type}", method = RequestMethod.GET)
	public @ResponseBody List<Notification> getNotification(@PathVariable String type){//get non confirmed notification for logged user
	
		
		User user=userService.getLoggedUser();
		if(user!=null){
		  List<Notification> notification= notificationService.getNonConfirmedNotification(user, type);
		  return notification;
		}else 
		  return null;
	}
	
	@RequestMapping(value="/notification/getAllNotification", method = RequestMethod.GET)
	public @ResponseBody List<Notification> getAllNotification(){//get notification for logged user
	
		
		User user=userService.getLoggedUser();
		if(user!=null){
		  List<Notification> notification= notificationService.getAllNotification(user);
		  return notification;
		}else 
		  return null;
	}
	
	@RequestMapping(value="/notification/confirmNotification/{id}", method = RequestMethod.GET)
	public  ResponseEntity<String>  confirmNotification(@PathVariable Long id){
	
		Notification notification = notificationService.findById(id);
		
		notification.setConfirmed(true);
		
		notificationService.merge(notification);
		return new ResponseEntity<>(HttpStatus.OK);
		
		//notification = notificationService.findById(id);
		//System.out.println(notification.isConfirmed());
	}
	
	@RequestMapping(value="/notification/remove", method = RequestMethod.POST)
	public  ResponseEntity<String>  confirmNotification(@RequestBody Notification note){
	
		notificationService.remove(note);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
