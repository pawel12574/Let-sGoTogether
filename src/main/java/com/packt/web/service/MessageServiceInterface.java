package com.packt.web.service;

import java.util.List;

import com.packt.web.bean.Message;

public interface MessageServiceInterface {

	public List<Message> getMessage(Long id);
	public Message getMessageById(Long id);
	public void removeMessageByChatId(Long id);
	public void update(Message m);
	public Message addMessage(Message message);
	
}
