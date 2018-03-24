package com.packt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.ChatDaoInterface;
import com.packt.web.bean.Chat;

public interface ChatServiceInterface {

	public Chat getChat(Long id);
	public void remove(Long chatId);
	public void addChatAndMessage(Chat chat);
	
	
}
