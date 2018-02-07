package com.packt.web.Dao;



import com.packt.web.bean.User;
import com.packt.web.bean.UserRating;

public interface UserRatingDaoInterface {
	
	public void addUser(UserRating userRating);
	public void mergeUser(UserRating userRating);
	public String getUserRating(int userId);
	public void removeUserRating(int id);
	public boolean isRatedByUser(int tripId, int authorId);
	
}
