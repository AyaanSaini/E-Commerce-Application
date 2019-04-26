package com.punjabifashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.punjabifashion.beans.Rating;
import com.punjabifashion.utils.DBUtil;

public class RatingDAOImpl implements RatingDAO {

	@Override
	public double getProductAvgRating(String productId) throws SQLException {
		System.out.println("-->RatingDAO:getProductAvgRating()");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String query =  "SELECT avg(rating) from rating_table where fk_product_id=?";
		double rating = 0;
		
		try {
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(productId));
			rs = ps.executeQuery();
			if(rs.next())
				rating = rs.getDouble(1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("<--RatingDAO:getProductAvgRating()");
		return rating;
	}

	@Override
	public double getProductUserRating(String userId, String productId) throws SQLException {
		System.out.println("-->RatingDAO:getProductUserRating()");
		String query =  "SELECT rating from rating_table where fk_product_id=? AND fk_user_id =?";
		double rating = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(productId));
			ps.setInt(2, Integer.parseInt(userId));
			rs = ps.executeQuery();
			if(rs.next())
				rating = rs.getDouble(1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(ps != null)ps.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("<--RatingDAO:getProductUserRating()");
		return rating;
	}

	@Override
	public int addRating(int productId, double productRating,String userName) throws SQLException  {
		System.out.println("-->RatingDAO:addRating()");
		int res = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "insert into rating_table(fk_product_id,fk_user_id,rating) values(?,?,?)";
		
		try {
			
			int userId = new UserDAOImpl().getUserId(userName);
			con  = DBUtil.getDBUtilInstance().getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, userId);
			ps.setDouble(3, productRating);
			res = ps.executeUpdate();
			con.commit();
		}catch(java.sql.SQLIntegrityConstraintViolationException se) {
			se.printStackTrace();
			res =2;
		}catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)ps.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("<--RatingDAO:addRating()");
		return res;
	}

}
