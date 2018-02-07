package com.packt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.ChatDaoInterface;
import com.packt.web.bean.Chat;

@Transactional
@Service
public class ChatService implements ChatServiceInterface {

	@Autowired
	ChatDaoInterface chatDao;
	
	public Chat getChat(int id) {
		return chatDao.getChatByTripId(id);
		
	}
	
	public void remove(int chatId){
		chatDao.remove(chatId);
	}

	public void addChatAndMessage(Chat chat) {
		chatDao.addChatAndMessage(chat);
		
	}

}
