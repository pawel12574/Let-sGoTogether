package com.packt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packt.web.bean.Taxi;
import com.packt.web.bean.Trip;
import com.packt.web.service.TaxiServiceInterface;

@Controller
public class TaxiController {
	
	@Autowired
	TaxiServiceInterface taxiService;
	
	@RequestMapping(value="/taxi/add", method = RequestMethod.POST)
	public ResponseEntity<String> addTaxi(@RequestBody Taxi taxi){
		taxiService.save(taxi);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/taxi/update", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody Taxi taxi){
		taxiService.update(taxi);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/taxi/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Taxi getTaxi(@PathVariable int id){
		return taxiService.getById(id);
	}
	
	@RequestMapping(value="/taxi/get/all", method = RequestMethod.GET)
	public @ResponseBody List<Taxi> getAllTaxi(){
		return taxiService.getAll();
	}
	
	@RequestMapping(value="/taxi/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable int id){
		taxiService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
