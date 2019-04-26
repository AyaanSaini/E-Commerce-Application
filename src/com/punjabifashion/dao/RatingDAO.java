package com.punjabifashion.dao;

import java.sql.SQLException;

import com.punjabifashion.beans.Rating;

public interface RatingDAO {

	public double getProductAvgRating(String productId) throws SQLException;
	public double getProductUserRating(String userId, String productId) throws SQLException;
	int addRating(int productId, double productRating, String userName) throws SQLException;
}
