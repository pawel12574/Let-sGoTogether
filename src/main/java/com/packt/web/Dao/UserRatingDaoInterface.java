package com.packt.web.Dao;



import com.packt.web.bean.UserRating;

public interface UserRatingDaoInterface {
	
	public void addUser(UserRating userRating);
	public void mergeUser(UserRating userRating);
	public String getUserRating(Long userId);
	public void removeUserRating(Long id);
	public boolean isRatedByUser(Long tripId, Long authorId);
	
}
