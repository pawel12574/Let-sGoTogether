package com.packt.web.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Chat;

@Transactional
@Repository
public class ChatDao implements ChatDaoInterface {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public Chat getChatByTripId(Long id) {
		Query query=entityManager.createQuery("select c from Chat c left join fetch c.messages where c.trip.id='"+id+"'");
		List<Chat> result = query.getResultList();
		if(result.isEmpty())
			return null; 
			else
		return result.get(0);
		
	}
	
	public void remove(Long chatId){
		Query query=entityManager.createQuery("delete Chat where id="+chatId+"");
		query.executeUpdate();
	}

	public void addChatAndMessage(Chat chat) {
		entityManager.merge(chat);
	}

}
