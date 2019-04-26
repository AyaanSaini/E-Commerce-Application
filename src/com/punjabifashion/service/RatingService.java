package com.punjabifashion.service;

import java.sql.SQLException;

public interface RatingService {
	
	public int addRating(int productId,double productrating,String userName) throws SQLException;
	public double getProductAvgRating(String productId) throws SQLException;
}
