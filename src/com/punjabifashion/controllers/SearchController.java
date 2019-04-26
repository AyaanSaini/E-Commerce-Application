package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;
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

@WebServlet("/Search")
public class SearchController extends HttpServlet {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	String message;

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		ProductDAO productDAO = new ProductDAOImpl();
		List<String[]> list;
		System.out.println("-->Search Controller");
		
		try{
			session = request.getSession();
		}catch(Exception e){
			message = "Session is null please login first";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		if(session.getAttribute("session_user_role") == null){
			message = "User is not a admin please login first";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}else if(session.getAttribute("session_user_role").equals("admin")){
			String productId = request.getParameter("productId");
			String productTitle = request.getParameter("productTitle");
			String pageName = request.getParameter("pageName");
			try{
				if(!productId.isEmpty()){
					System.out.println("ProductId = "+productId);
					list = productDAO.searchProduct("productId",productId);
					System.out.println(list);
				}else if(!productTitle.isEmpty()){
					System.out.println("Product Title = "+productTitle);
					list = productDAO.searchProduct("productTitle",productTitle);
					System.out.println(list);
				}else{
					System.out.println("All");
					list = productDAO.searchProduct("","");
					System.out.println(list);
				}
				message = "Product List";
				request.setAttribute("success", list);
				System.out.println("success == "+request.getAttribute("success"));
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/jsp/admin/"+pageName+".jsp");
				rd.forward(request, response);
				response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/editproduct.jsp");
			}catch(Exception e){
				message = e.getMessage();
				request.setAttribute("failure", message);				
				e.printStackTrace();
				RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/jsp/admin/"+pageName+".jsp");
				rd.forward(request, response);
			}
		}
	}
}
