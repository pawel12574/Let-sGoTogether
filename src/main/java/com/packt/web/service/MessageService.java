package com.packt.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.MessageDaoInterface;
import com.packt.web.bean.Message;
import com.packt.web.bean.User;

@Service
@Transactional
public class MessageService implements MessageServiceInterface{

	@Autowired
	MessageDaoInterface messageDao;
	@Autowired
	UserServiceInterface userService;
	
	public List<Message> getMessage(Long id) {
		
		return messageDao.getMessageByTrip(id);
		
	}
	
	public Message getMessageById(Long id){
		return messageDao.getMessageById(id);
	}
	
	public void removeMessageByChatId(Long id){
		messageDao.removeMessage(id);
	}
	
	public void update(Message m){
		messageDao.merge(m);
	}

	public Message addMessage(Message message) {
		User user=userService.getLoggedUser();
		message.setAuthor(user);
		return messageDao.addMessage(message);
	}
	

}
