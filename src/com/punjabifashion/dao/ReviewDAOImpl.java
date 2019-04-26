package com.punjabifashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.punjabifashion.beans.Review;
import com.punjabifashion.utils.DBUtil;

public class ReviewDAOImpl implements ReviewDAO {

	@Override
	public int addComment(String review, int productId, String userName) throws SQLException {
		System.out.println("-->ReviewDAO:addComment()");
		int res = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String query = "insert into comment_table(fk_product_id,fk_user_id,comment) values(?,?,?)";
		try {
			
			int userId = new UserDAOImpl().getUserId(userName);
			con  = DBUtil.getDBUtilInstance().getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, userId);
			ps.setString(3, review);
			res = ps.executeUpdate();
			con.commit();
		}catch(java.sql.SQLIntegrityConstraintViolationException se) {
			se.printStackTrace();
			res =2;
		} catch (SQLException se) {
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
		System.out.println("<--ReviewDAO:addComment()");
		return res;
	}

	@Override
	public List<String[]> getProductReviews(String productId) throws SQLException {
		System.out.println("-->ReviewDAO:getProductReviews()");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String query =  "SELECT DISTINCT c.comment,u.username,c.comment_id,r.rating from user_table u, comment_table c, rating_table r where c.fk_product_id=? AND u.pk_user_id = c.fk_user_id AND r.fk_user_id = c.fk_user_id AND r.fk_product_id = ?";
		List<String[]> list = new ArrayList<String[]>();
		try {
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(productId));
			ps.setInt(2, Integer.parseInt(productId));
			rs = ps.executeQuery();
			while(rs.next()){
				String[] str = new String[4];
				str[0] = rs.getInt(3)+"";
				str[1] = rs.getString(2);
				str[2] = rs.getString(1);
				str[3] = rs.getString(4);
				list.add(str);
			}
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
		System.out.println("<--ReviewDAO:getProductReviews()");
		return list;
	}

}
