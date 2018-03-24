package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Message;


public interface MessageDaoInterface {
	
	public List<Message> getMessageByTrip(Long id);
	public Message getMessageById(Long id);
	public void removeMessage(Long id);
	public void merge(Message m);
    public Message addMessage(Message message);
}
