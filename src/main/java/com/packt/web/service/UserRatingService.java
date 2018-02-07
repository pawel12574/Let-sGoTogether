package com.packt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.web.Dao.UserRatingDao;
import com.packt.web.Dao.UserRatingDaoInterface;
import com.packt.web.bean.User;
import com.packt.web.bean.UserRating;


@Service
@Transactional
public class UserRatingService implements UserRatingServiceInterface {

	@Autowired
	UserRatingDaoInterface userRatingDao;
	@Autowired
	UserServiceInterface userService;
	
	
	public void addUserRating(UserRating userRating) {
		
		userRatingDao.addUser(userRating);
	}
	
	public void removeUserRating(int id){
		userRatingDao.removeUserRating(id);
	}

	
	public void mergeUser(UserRating userRating) {
		
		userRatingDao.mergeUser(userRating);
		
	}

	
	public String getUserRating(int userId) {
		
		return userRatingDao.getUserRating(userId);
	}
	
	public boolean isRatedByUser(int tripId){
		
		return userRatingDao.isRatedByUser(tripId, userService.getLoggedUser().getId());
	}

}
