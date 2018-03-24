package com.packt.web.Dao;

import com.packt.web.bean.Chat;

public interface ChatDaoInterface {
	
	public Chat getChatByTripId(Long id);
	public void remove(Long chatId);
    public void addChatAndMessage(Chat chat);
}
