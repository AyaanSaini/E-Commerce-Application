package com.punjabifashion.dao;

import java.sql.SQLException;
import java.util.List;

import com.punjabifashion.beans.Product;

public interface ProductDAO {

	public int addProduct(Product product) throws SQLException;
	public int updateProduct(int productId,double productMrp ,double productDiscount)throws SQLException;
	public int deleteProduct(int productId)throws SQLException;
	public List<Product> getProducts()throws SQLException;
	public String[] getProduct(String id)throws SQLException;
	public List<String[]> searchProduct(String by,String value) throws SQLException;
	public byte[] getImage(int productId) throws SQLException;
	public List<Product> getFilteredProduct(String title, String gender, String category, String[] sizes)throws SQLException;
	public String getProductSizes(int productId)throws SQLException;
	
}
