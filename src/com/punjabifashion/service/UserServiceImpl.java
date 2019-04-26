package com.punjabifashion.service;

import java.sql.SQLException;

import com.punjabifashion.beans.User;
import com.punjabifashion.dao.UserDAO;
import com.punjabifashion.dao.UserDAOImpl;


public class UserServiceImpl implements UserService{
	UserDAO userDAO = new UserDAOImpl();
	@Override
	public String isValidUser(String username, String password) throws SQLException {
		
		return userDAO.isValidUser(username,password);

	}

	@Override
	public int addUser(User user) {
		int resCode = 0;
		try{
			resCode = userDAO.addUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resCode;
	}

	@Override
	public int updateUser(User user) {
		int res = 0;
		 try {
			res= userDAO.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return res;
	}

	
	@Override
	public int deleteUser(String userId) {
		return 0;
	}

	@Override
	public String[] forgetPassword(String username, String q1, String ans1, String q2, String ans2) {
		String[] res = new String[2];
		
		try {
			
			res = userDAO.forgetPassword(username,q1,ans1,q2,ans2);
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
