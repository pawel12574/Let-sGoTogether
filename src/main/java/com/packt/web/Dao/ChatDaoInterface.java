package com.packt.web.Dao;

import com.packt.web.bean.Chat;

public interface ChatDaoInterface {
	
	public Chat getChatByTripId(int id);
	public void remove(int chatId);
    public void addChatAndMessage(Chat chat);
}
