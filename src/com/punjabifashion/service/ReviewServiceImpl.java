package com.punjabifashion.service;

import java.sql.SQLException;
import java.util.List;

import com.punjabifashion.dao.ReviewDAO;
import com.punjabifashion.dao.ReviewDAOImpl;

public class ReviewServiceImpl implements ReviewService {

	ReviewDAO reviewDAO;

	@Override
	public List<String[]> getProductReview(String productId) {
		List<String[]> list = null;
		reviewDAO = new ReviewDAOImpl();
		try {
			list = reviewDAO.getProductReviews(productId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int addComment(String review, int productId, String userName) throws SQLException {
		int res = 0;
		reviewDAO = new ReviewDAOImpl();
		res = reviewDAO.addComment(review,productId,userName);
		return res;
	}

}
