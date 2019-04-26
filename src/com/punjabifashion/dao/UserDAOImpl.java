package com.punjabifashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.punjabifashion.beans.User;
import com.punjabifashion.utils.DBUtil;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public String isValidUser(String username, String password) throws SQLException {
		System.out.println("-->UserDAO:isValidUser()");
		String role = "noUser";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			con.setAutoCommit(false);
			String query = "select * from user_table where username = ? AND password = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				role = rs.getString("user_type");
			}
			con.commit();
		}
		catch(SQLException se){
			System.out.println(se.getMessage());			
		}catch(Exception e){
			System.out.println(e.getMessage());			
		}finally {try {
			if(rs != null)rs.close();
			if(ps != null)ps.close();
			DBUtil.getDBUtilInstance().closeConnection(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
		System.out.println("<--UserDAO:isValidUser()");
		return role;
	}

	@Override
	public int addUser(User user) throws SQLException{
		System.out.println("-->UserDAO:addUser()");
		int resCode = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			String query = "insert into user_table(username,email,password,question1,answer1,question2,answer2) values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getQuestion1());
				ps.setString(5,user.getAnswer1());
				ps.setString(6, user.getQuestion2());
				ps.setString(7, user.getAnswer2());
			int rs = ps.executeUpdate();
			if(rs == 1 )
				resCode = 1;
				System.out.println(rs);
			
		}
		catch(SQLException se){
			System.out.println(se.getMessage());
			resCode = 2;			
		}catch(Exception e){
			System.out.println(e.getMessage());			
		}finally {
			try {
				if(ps != null)ps.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("-->UserDAO:updateUser()");
		return resCode;
	}

	@Override
	public int updateUser(User user) throws SQLException {
		System.out.println("<--UserDAO:addUser()");
		int res = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "UPDATE user_table set password = ? WHERE username = ?;";
		
		try {
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getUsername());
			int rs = ps.executeUpdate();
			if(rs == 1)
				res= 1;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			res = 100;
		}catch (Exception e) {
			e.printStackTrace();
			res = -1;
		}finally {
			try {
				if(ps != null)ps.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("<--UserDAO:updateUser()");
		return res;
	}

	@Override
	public int deleteUser(User user) {
		return 0;
	}

	@Override
	public int getUserId(String username) throws SQLException {
		System.out.println("-->UserDAO:getUserId()");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = -1;
		String query = "SELECT pk_user_id FROM user_table WHERE username = ?";
			try{
				con  = DBUtil.getDBUtilInstance().getConnection();
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				rs = ps.executeQuery();
				if(rs.next())
					res= rs.getInt(1);
			}catch(SQLException se){
				System.out.println(se.getMessage());
				res = 100;
			}catch (Exception e) {
				e.printStackTrace();
				res = -1;
			}finally {
				try {
					if(rs != null)rs.close();
					if(ps != null)ps.close();
					DBUtil.getDBUtilInstance().closeConnection(con);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("<--UserDAO:getUserId()");
			return res;
	}

	@Override
	public String[] forgetPassword(String username, String q1, String ans1, String q2, String ans2) throws SQLException {
		System.out.println("-->UserDAO:forgetPassword()");
		Connection con = null;
		PreparedStatement ps = null,ps1 = null;
		ResultSet rs = null,rs1 = null;
		String[] res = new String[2];
		
		String query = "SELECT pk_user_id FROM user_table WHERE username = ?";
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				ps1 = con.prepareStatement("SELECT password FROM user_table WHERE username = ? AND question1 = ? AND answer1 = ? AND question2 = ? AND answer2 = ? ");
				ps1.setString(1, username);
				ps1.setString(2, q1);
				ps1.setString(3, ans1);
				ps1.setString(4, q2);
				ps1.setString(5, ans2);
				rs1 = ps1.executeQuery();
				if(rs1.next()) {
					res[0] = "fine";
					res[1] = rs1.getString(1);
				}
				else {
					res[0] = "question/answers"; 
				}
			}else {
				res[0] = "username";
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {			
			try {
				if(rs1 != null)rs.close();
				if(ps1 != null)ps.close();
				if(rs != null)rs.close();
				if(ps != null)ps.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("<--UserDAO:forgetPassword()");
	return res;

	}

}
