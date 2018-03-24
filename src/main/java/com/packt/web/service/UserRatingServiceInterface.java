package com.packt.web.service;

import com.packt.web.bean.User;
import com.packt.web.bean.UserRating;

public interface UserRatingServiceInterface {


	public void addUserRating(UserRating userRating);
	public void removeUserRating(Long id);
	public void mergeUser(UserRating userRating);
	public String getUserRating(Long userId);
	public boolean isRatedByUser(Long tripId);
}
