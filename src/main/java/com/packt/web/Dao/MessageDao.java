package com.packt.web.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.bean.Message;
import com.packt.web.bean.Trip;

@Repository
public class MessageDao implements MessageDaoInterface {
   
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public List<Message> getMessageByTrip(Long id) {
		Query query=entityManager.createQuery("select c.messages from Chat c, Trip t WHERE t.id='"+id+"' and c.id=t.chat.id");
		List<Message> result = query.getResultList();
		return result;
		
	}
	
	@Transactional
	public Message getMessageById(Long id) {
		Query query=entityManager.createQuery("select m from Message m WHERE m.id='"+id+"'");
		List<Message> result = query.getResultList();
		if(!result.isEmpty())
		   return result.get(0);
		else
		   return null;
		
	}
	
	@Transactional
	public void removeMessage(Long id) {
		Query query=entityManager.createQuery("delete Message where chat.id='"+id+"'");
		query.executeUpdate();
		
	}
	
	@Transactional
	public void merge(Message m){
		entityManager.merge(m);
	}
	/* 
	@Transactional
	public List<Message> getMessageByTrip(int id) {
		Query query=entityManager.createQuery("select t.chat.id from Trip t where t.id="+id+"");
		int chatId = (int ) query.getSingleResult();
		query=entityManager.createQuery("select c.messages from  Chat c WHERE c.id='"+chatId+"'");
		List<Message> result = query.getResultList();
		return result;
		
	}*/
    @Transactional
	public Message addMessage(Message message) {
		
		entityManager.persist(message);
		return message;
	}

	
	
}
