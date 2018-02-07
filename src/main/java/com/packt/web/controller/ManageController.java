package com.packt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageController {

	@RequestMapping(value="/admin/view")
	public String adminPage(){
		return "admin";
	}
	
	@RequestMapping(value="/admin/view/listTrip")
	public String adminListTrip(){
		return "adminListTrip";
	}
	
	@RequestMapping(value="/admin/view/listTripSd")
	public String adminListTripSd(){
		return "adminListTripSd";
	}
	
	@RequestMapping(value="/admin/view/listUsers")
	public String adminListUsers(){
		return "adminListUsers";
	}
	
	@RequestMapping(value="/admin/view/listTaxi")
	public String adminListTaxi(){
		return "adminListTaxi";
	}
	
}
