package com.punjabifashion.service;

import java.sql.SQLException;
import com.punjabifashion.dao.RatingDAOImpl;

public class RatingServiceImpl implements RatingService {



	@Override
	public double getProductAvgRating(String productId) throws SQLException {
		double avgrating = new RatingDAOImpl().getProductAvgRating(productId);
		return avgrating;
	}


	@Override
	public int addRating(int productId, double productrating,String userName) throws SQLException {
		int res = 0 ;
		res = new RatingDAOImpl().addRating(productId,productrating,userName);			
		return res;
	}

}
