package com.packt.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.packt.web.bean.Chat;
import com.packt.web.bean.Message;
import com.packt.web.bean.User;
import com.packt.web.dto.MessageDto;
import com.packt.web.service.ChatServiceInterface;
import com.packt.web.service.DateService;
import com.packt.web.service.MessageServiceInterface;
import com.packt.web.service.TripServiceInterface;
import com.packt.web.service.UserServiceInterface;

@Controller
public class MessageController {

	@Autowired
	MessageServiceInterface messageService;
	@Autowired
	ChatServiceInterface chatService;
	@Autowired
	UserServiceInterface userService;
	@Autowired
	TripServiceInterface tripService; 
	
	
	@RequestMapping(value="/chat/get/{id}", method=RequestMethod.GET) //trip id
	public @ResponseBody List<Message> chatGet(@PathVariable Long id ){
		
		List<Message> result = messageService.getMessage(id);
		for(Message m:result){//convert Date to String
			m.setDate(DateService.dateToString(m.getCreated()));
		}
		return result;
	} 
	
	@RequestMapping(value="/message/add", method=RequestMethod.POST)
	public @ResponseBody MessageDto messageAdd(@RequestBody MessageDto message ){
		
		Message msg=new Message();
		msg.setContents(message.getContents());
		msg.setAuthor(userService.getLoggedUser());

		Long tripId = message.getTripId();
	
		if(chatService.getChat(message.getTripId())== null){
		   
			Set<Message> messages=new HashSet<Message>();
			Chat chat=new Chat(tripService.getTripDetails(tripId), messages);// konstruktor(trip, Set<Messages>)
			msg.setChat(chat);
			
			messages.add(msg);
			
			chat.getMessages().add(msg);
			
			chatService.addChatAndMessage(chat); //chat update
			return message;
			
		}else {
		
	
		Chat chat = chatService.getChat(tripId);
		msg.setChat(chat);
		chat.getMessages().add(msg);
		
		chatService.addChatAndMessage(chat);  //chat update
	    return message;
	    
	    } 
	
	}
	
	@RequestMapping(value="/message/update", method=RequestMethod.POST)
	public  ResponseEntity<String> messageUpdate(@RequestBody Message message ){
		
		Message msg = messageService.getMessageById(message.getId());
		
		msg.setContents(message.getContents());
		messageService.update(msg);
		
		return new ResponseEntity<>(HttpStatus.OK);
	
	}
	
	
	
	
}
