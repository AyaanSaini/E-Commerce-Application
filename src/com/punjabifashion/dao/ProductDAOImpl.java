package com.punjabifashion.dao;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.punjabifashion.beans.Product;
import com.punjabifashion.utils.DBUtil;

public class ProductDAOImpl implements ProductDAO {
	
	
	@Override
	public int addProduct(Product product) throws SQLException {
		System.out.println("-->productDAO:addProduct");
		Connection con = null;
		PreparedStatement ps1 = null,ps = null,ps2 = null;
		ResultSet rs = null;
		int res =0; 
		//String code = null;
		//code = product.getCategory().substring(0, 2)+product.getSubCategory().substring(0, 2)+DBUtil.getLastIndexProduct();
		//product.setCode(code);
		
		
		if(product != null){
			System.out.println("-->productDAO:addProduct::"+product);
			String q1 = "insert into product_table(product_name,product_description,product_price,product_discount,product_color,photo,product_catagory,product_fit,stiched,product_gender) values(?,?,?,?,?,?,?,?,?,?)";
			String q2 = "insert into product_sizes_table(fk_product_id,size) values(?,?) ";
			int productId = 0;
			String category;;
			String productCategory = product.getCategory();
			if(productCategory.equalsIgnoreCase("gottapatti")||productCategory.equalsIgnoreCase("Bangles")) {
				category = "Others";
			}else {
				category = product.getCategory();
			}
			String productSizes[] = product.getSizes();//null
			try {
				con  = DBUtil.getDBUtilInstance().getConnection();
				con.setAutoCommit(false);
				ps1 = con.prepareStatement(q1);
					ps1.setString(1, product.getName());
					ps1.setString(2, product.getDescription());
					ps1.setDouble(3, product.getMrp());
					ps1.setDouble(4, product.getDiscount());
					ps1.setString(5, product.getColors());
					ps1.setBlob(6, product.getPhoto());
					ps1.setString(7, category);
					ps1.setString(8, product.getFit());
					ps1.setBoolean(9, product.isStitched());
					ps1.setString(10,product.getGender());
					System.out.println("Created Query = "+ps1.toString());
				ps1.executeUpdate();
				
				ps = con.prepareStatement("select pk_product_id from product_table ORDER BY pk_product_id DESC LIMIT 1");
				rs = ps.executeQuery();
				if(rs.next()){
					 productId= rs.getInt(1);
					 if(productSizes == null) {
						 	ps2 = con.prepareStatement(q2);
							ps2.setInt(1, productId);
							ps2.setString(2, "free");
							System.out.println("Created Query = "+ps1.toString());
							ps2.executeUpdate();
					 }else {
						for(int i=0;i<productSizes.length;i++){
								ps2 = con.prepareStatement(q2);
								ps2.setInt(1, productId);
								ps2.setString(2, productSizes[i]);
								System.out.println("Created Query = "+ps1.toString());
							ps2.executeUpdate();
						}
					 }
					con.commit();
					res= 1;
				}else{
					con.rollback();
					res=0;
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(ps1 != null)ps.close();
					if(rs != null)rs.close();
					if(ps != null)ps.close();					
					if(ps2 != null)ps1.close();
					if(ps2 != null)ps2.close();
					DBUtil.getDBUtilInstance().closeConnection(con);
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
			
			
		}else{
			res=100;
		}
		System.out.println("<--productDAO:addProduct");
		return res;
	}
	
	@Override
	public int deleteProduct(int productId) throws SQLException {
		System.out.println("-->productDAO:deleteProduct");
		Connection con = null;
		PreparedStatement ps = null;
		int res=0;
		String query = "DELETE from product_table WHERE pk_product_id = ?";
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);			
			ps.executeUpdate();
			res =1;
		}catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			res=100;
		}finally {
			try {
				if(ps != null)ps.close();
				DBUtil.getDBUtilInstance().closeConnection(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		System.out.println("<--productDAO:deleteProduct");
		return res;
	}

	
	@Override
	public List getProducts() throws SQLException {
		System.out.println("-->ProductDAO:getProducts()");
		Connection con = null;
		PreparedStatement ps = null,ps1 = null;
		ResultSet rs = null,rs1 = null;
		List list = new ArrayList();
		double rating = 0.0;
		
		String query = "SELECT pk_product_id,product_title,product_price,product_discount FROM product_table;";
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);			
			rs = ps.executeQuery();			
			
			while(rs.next()){
				String[] str = new String[5];
				OutputStream outputStream;
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = Double.toString(rs.getDouble(3));
				str[3] = Double.toString(rs.getDouble(4));
				
				ps1 = con.prepareStatement("SELECT avg(rating) from rating_table where fk_product_id=?");
				ps1.setInt(1, Integer.parseInt(str[0]));
				rs1 = ps1.executeQuery();				
				if(rs1.next())
					rating = rs1.getDouble(1);
				str[4] = rating+"";				
				list.add(str);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e){
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
		System.out.println("<--ProductDAO:SearchProduct");
		return list;
	}

	@Override
	public String[] getProduct(String id) throws SQLException {
		System.out.println("-->ProductDAO:getProduct");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query;
		String[] str = new String[6];
		
			query = "SELECT pk_product_id,product_title,product_price,product_discount,product_description from product_table WHERE pk_product_id =?";
			//query2 = "SELECT AVG(rating) from rating_table GROUP BY fk_product_id where fk_product_id=?";
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));			
			rs = ps.executeQuery();			
			if(rs.next()){
				
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(5);
				str[3] = Double.toString(rs.getDouble(3));
				str[4] = Double.toString(rs.getDouble(4));
				
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
		System.out.println("<--ProductDAO:getProduct");
		return str;
	}

	@Override
	public List<String[]> searchProduct(String by, String value) throws SQLException {
		System.out.println("-->ProductDAO:SearchProduct");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query;
		List<String[]> list = new ArrayList<String[]>();
		if(by.equalsIgnoreCase("productId")){
			query = "SELECT pk_product_id,product_name,product_title,product_price,product_discount from product_table WHERE pk_product_id = "+value;
		}
		else if(by.equalsIgnoreCase("productTitle")){
			query = "SELECT pk_product_id,product_name,product_title,product_price,product_discount from product_table WHERE product_title LIKE '%"+value+"%'";
		}
		else{
			query = "SELECT pk_product_id,product_name,product_title,product_price,product_discount from product_table";
		}
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);					
			rs = ps.executeQuery();
		
			while(rs.next()){
				String[] str = new String[5];
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = rs.getString(3);
				str[3] = Double.toString(rs.getDouble(4));
				str[4] = Double.toString(rs.getDouble(5));
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
		System.out.println("<--ProductDAO:SearchProduct");
		return list;
	}

	@Override
	public int updateProduct(int productId, double productMrp, double productDiscount) throws SQLException {
		
		System.out.println("-->productDAO:editProduct");
		Connection con = null;
		PreparedStatement ps = null;
		int res=0;
		String query = "UPDATE product_table SET product_discount = ?, product_price = ? WHERE pk_product_id = ?";
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setDouble(1, productDiscount);
			ps.setDouble(2, productMrp);
			ps.setInt(3, productId);
			ps.executeUpdate();
			res =1;
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
			
		System.out.println("<--productDAO:editProduct");
		return res;
	}

	@Override
	public byte[] getImage(int productId) throws SQLException {
		System.out.println("-->ProductDAO:getImage");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		byte[] image = null;
		String query = "SELECT photo from product_table where pk_product_id = ?";
		try{
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if(rs.next()){
				image = rs.getBytes(1);
			}
			System.out.println("<--ProductDAO:getImage");
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
		return image;
	}

	@SuppressWarnings("unchecked")
	public List getFilteredProduct(String title, String gender, String category, String[] sizes) throws SQLException{
		
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null,ps1 = null;
		ResultSet rs = null,rs1 = null;
		double rating = 0.0;
		
		String query = "SELECT pk_product_id,product_title,product_price,product_discount FROM product_table p";
		System.out.println("sizes = "+sizes.length);
		System.out.println("Title = "+title);
		System.out.println("Gender = "+gender);
		System.out.println("Category = "+category);
		if(sizes != null && sizes[0] != null && !sizes[0].equals("")) {
			query = query+", product_size_table ps";
		}
		if((!title.equals(""))|| (!gender.equals("")) || (!category.equals(""))) {
			query = query+" WHERE ";
		}
		if(!title.equals("")) {
			query = query+"p.product_title like '%"+title+"%' AND ";
		}
		if(gender !=null &&!gender.equals("")) {
			query = query+"p.product_gender = '"+gender+"' AND ";
		}
		if(category!= null && !category.equals("")) {
			query = query+"p.product_catagory = '"+category+"' AND ";
		}
		if((sizes != null) && (sizes[0] !=null && !sizes[0].equals(""))) {
			System.out.println("inside size");
			query = query+"("; 
			for(int i=0;i<sizes.length;i++) {
				if(sizes[i] != null)
					query = query+"ps.size = '"+sizes[i]+"' OR ";
			}
			
			if(query.substring(query.length()-3, query.length()).equalsIgnoreCase("or ")) {
				System.out.println("inside first substring");
				query = query.substring(0, query.length()-3) + ") AND ";
				query = query + "ps.fk_product_id = p.pk_product_id;";
			}
		}
		String s = query.substring(query.length()-4, query.length());
		System.out.println(query.substring(query.length()-4, query.length())+"\tSize"+s.length());
		if(query.substring(query.length()-4, query.length()).equalsIgnoreCase("AND ")) {
			System.out.println("inside second substring");
			query = query.substring(0, query.length()-4);
		}
		
		System.out.println("Filtered Product query =: "+query);
		
		
		try {
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps =  con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				String[] str = new String[5];
				OutputStream outputStream;
				str[0] = rs.getString(1);
				str[1] = rs.getString(2);
				str[2] = Double.toString(rs.getDouble(3));
				str[3] = Double.toString(rs.getDouble(4));
				
				ps1 = con.prepareStatement("SELECT avg(rating) from rating_table where fk_product_id=?");
				ps1.setInt(1, Integer.parseInt(str[0]));
				rs1 = ps1.executeQuery();				
				if(rs1.next())
					rating = rs1.getDouble(1);
				str[4] = rating+"";	
				list.add(str);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}catch(Exception e){
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
		return list;
	}

	public String getProductSizes(int productId)throws SQLException {
		String sizes = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer("");
		String query = "select size from product_sizes_table where fk_product_id = ?";
		try {
			con  = DBUtil.getDBUtilInstance().getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				sb.append(rs.getString(1)+",");
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
		sb.deleteCharAt(sb.length()-1);
		sizes = sb.toString();
		return sizes;
	}

}
