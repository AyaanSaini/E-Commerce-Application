package com.punjabifashion.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	Connection con;
	static String database = "punjab-fashion";
	static String url = "jdbc:mysql://localhost:3306/";
	static String username = "root";
	static String password = "root";
	private DBUtil() {}
	
	private static class DBUtilHelper{
		private static final DBUtil dbUtil = new DBUtil();		
	}
	public static DBUtil getDBUtilInstance() {
		return DBUtilHelper.dbUtil;
	}
	public Connection getConnection() {
		try{
			if(con == null || con.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url+database,username,password);
			}
		}
		catch(SQLException se){
			System.out.println("SQLException::"+se);
		}catch(ClassNotFoundException ce){
			System.out.println("ClassNotFoundException::"+ce);
		}
		return con;
		
	}
	
	public void closeConnection(Connection con) {
		try {
			if(!con.isClosed()) {
				con.close();
			}
			else
				return;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getLastIndexProduct(){
		int index = 0;
		String lastIndexQuery ="SELECT * FROM product_table ORDER BY pk_product_id DESC LIMIT 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
			try {
				con = new DBUtil().getConnection();
				ps = con.prepareStatement(lastIndexQuery);
				rs =  ps.executeQuery();
				if(rs.next()){
					index = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		return index;
	}
	
	

}
