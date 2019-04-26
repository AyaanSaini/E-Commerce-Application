package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.dao.ProductDAO;
import com.punjabifashion.dao.ProductDAOImpl;
import com.punjabifashion.service.ProductService;
import com.punjabifashion.validators.ProductValidation;

@WebServlet("/EditDetails")
public class EditDetailsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String message;
	ProductDAO productDAO;
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
			System.out.println("Admin user");
			
			String id= request.getParameter("productId");
			if(id != null ){
				System.out.println("Product id = "+id);
				productDAO = new ProductDAOImpl();
				List<String[]> list;
				try {
					list = productDAO.searchProduct("productId",id );
					if(!list.isEmpty()){
						System.out.println("List is not empty");
						request.setAttribute("productData", list);
						RequestDispatcher rd=request.getRequestDispatcher("/jsp/admin/editproductdetails.jsp");
						rd.forward(request, response);
				}else{
					message = "Product Not Found";
					request.setAttribute("message", message);
					RequestDispatcher rd=request.getRequestDispatcher("/jsp/admin/editproductdetails.jsp");
					rd.forward(request, response);
				}
				} catch (SQLException e) {
					message = e.getMessage();
					request.setAttribute("failure", message);				
					e.printStackTrace();
					response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
				}
			}
		}
	
	}
}
