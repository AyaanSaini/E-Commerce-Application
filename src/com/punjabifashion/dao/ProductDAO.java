package com.punjabifashion.dao;

import java.sql.SQLException;
import java.util.List;

import com.punjabifashion.beans.Product;

public interface ProductDAO {

	public int addProduct(Product product) throws SQLException;
	public int updateProduct(Product product)throws SQLException;
	public int deleteProduct(Product product)throws SQLException;
	public List<Product> getProducts()throws SQLException;
	public Product getProduct(String id)throws SQLException;
	public List<String[]> searchProduct(String by,String value) throws SQLException;
	
}
