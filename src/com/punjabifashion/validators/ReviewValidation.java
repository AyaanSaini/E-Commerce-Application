package com.punjabifashion.validators;

public class ReviewValidation {	

	public boolean reviewValidation(String comment, int productId, String user) {
		// TODO Auto-generated method stub
		if(comment.equals("")) {
			return false;
		}else
			return true;
	}

}
