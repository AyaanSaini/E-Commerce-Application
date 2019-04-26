package com.punjabifashion.service;

import java.sql.SQLException;
import java.util.List;

import com.punjabifashion.beans.Product;

public interface ProductService {

	public int addProduct(Product product) throws SQLException;
	public int updateProduct(int productId,double productDiscount,double productMrp)throws SQLException;
	public int deleteProduct(int productId)throws SQLException;
	public List<Product> getProducts()throws SQLException;
	public String[] getProduct(String id)throws SQLException;
	@SuppressWarnings("rawtypes")
	public List getFilteredProducts(String titleFilter, String genderFilter, String categoryFilter, String[] sizes) throws SQLException;
	public String getProductSizes(int productId)throws SQLException;
	
}
