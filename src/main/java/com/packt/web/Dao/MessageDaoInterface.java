package com.packt.web.Dao;

import java.util.List;

import com.packt.web.bean.Message;


public interface MessageDaoInterface {
	
	public List<Message> getMessageByTrip(int id);
	public Message getMessageById(int id);
	public void removeMessage(int id);
	public void merge(Message m);
    public Message addMessage(Message message);
}
