package com.packt.web.service;

import java.util.List;

import com.packt.web.bean.Message;

public interface MessageServiceInterface {

	public List<Message> getMessage(int id);
	public Message getMessageById(int id);
	public void removeMessageByChatId(int id);
	public void update(Message m);
	public Message addMessage(Message message);
	
}
