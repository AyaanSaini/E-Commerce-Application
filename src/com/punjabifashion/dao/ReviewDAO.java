package com.punjabifashion.dao;

import java.sql.SQLException;
import java.util.List;

import com.punjabifashion.beans.Review;

public interface ReviewDAO {
	
	public int addComment(String review, int productId, String userName) throws SQLException;
	public List<String[]> getProductReviews(String productId) throws SQLException;
}
