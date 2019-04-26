package com.punjabifashion.service;

import java.sql.SQLException;
import java.util.List;

import com.punjabifashion.beans.Product;
import com.punjabifashion.dao.ProductDAO;
import com.punjabifashion.dao.ProductDAOImpl;

public class ProductServiceImpl implements ProductService{

	@Override
	public int addProduct(Product product) throws SQLException {
		System.out.println("-->productService:addProduct");
		int res = 0;
		ProductDAO  productDao;
		try {
			if(product !=null){
				productDao= new ProductDAOImpl();
				res = productDao.addProduct(product);
			}
			else{
				res = 100;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("<--productService:addProduct");
		return res;
	}

	

	@Override
	public int deleteProduct(int productId) {
		int res=0;
		ProductDAO productDAO = new ProductDAOImpl();
		try{
			res = productDAO.deleteProduct(productId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getProducts() {
		System.out.println("-->ProductService:getProducts()");
		List res = null;
		ProductDAO productDAO = new ProductDAOImpl();
		try{
			res = productDAO.getProducts();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("<--ProductService:getProducts()");
		return res;
	}

	@Override
	public String[] getProduct(String id) {
		String[] res = null;
		ProductDAO productDAO = new ProductDAOImpl();
		try{
			res = productDAO.getProduct(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}



	@Override
	public int updateProduct(int productId, double productDiscount, double productMrp) throws SQLException {
		int res=0;
		ProductDAO productDAO = new ProductDAOImpl();
		try{
			res = productDAO.updateProduct(productId, productMrp,productDiscount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getFilteredProducts(String titleFilter, String genderFilter, String categoryFilter, String[] sizes)
			throws SQLException {
		List<Product> list =null;
		try {
			list = new ProductDAOImpl().getFilteredProduct(titleFilter,genderFilter,categoryFilter,sizes);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public String getProductSizes(int productId)throws SQLException {
		String sizes = null;
		try {
			sizes = new ProductDAOImpl().getProductSizes(productId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sizes;
		
	}



	

}
