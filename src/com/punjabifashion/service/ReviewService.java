package com.punjabifashion.service;

import java.sql.SQLException;
import java.util.List;

public interface ReviewService {
	
	public int addComment(String review,int productId,String userName) throws SQLException;	
	public List<String[]> getProductReview(String productId);
}
