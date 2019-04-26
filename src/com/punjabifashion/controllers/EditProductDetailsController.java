package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.service.ProductService;
import com.punjabifashion.service.ProductServiceImpl;

@WebServlet("/EditProductDetails")
public class EditProductDetailsController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String message;
	ProductService productService;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("-->EditDetailsController");
		try{
			session = request.getSession();
		}catch(Exception e){
			message = "Session is null please login first";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		if(session.getAttribute("session_user_role") == null){
			message = "User is not a admin please login first";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		else if(session.getAttribute("session_user_role").toString().equalsIgnoreCase("admin")){
			int res = 0;
			String mrp = request.getParameter("productmrp");
			String discount = request.getParameter("productdiscount");
			String id = request.getParameter("id");
			System.out.println(id+" "+mrp+" "+discount);
			int productId = Integer.parseInt(id);
			double productMrp =Double.parseDouble(mrp);
			double productDiscount = Double.parseDouble(discount);
			productService = new ProductServiceImpl();
			try {
				res = productService.updateProduct(productId, productDiscount, productMrp);
				if(res == 1){
					message = "Product Edited Successfully";
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/editproduct.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				}else if(res == 2){
					message = "Product Not Found";
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/editproduct.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
				}
				else{
					message = "Product Not Edited, Contact to Syatem Admin";
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/editproduct.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				message="Database Error Contact system admin";
				response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
			}
			
		}
	}
}