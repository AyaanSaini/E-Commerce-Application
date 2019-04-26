package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.service.ReviewService;
import com.punjabifashion.service.ReviewServiceImpl;

@WebServlet("/Comments")
public class ReviewsController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String error;
	String message;
	ReviewService reviewService;
	List<String[]> list = null;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try{
			session = request.getSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		String user = (String) session.getAttribute("session_user");
		if(user == null || user.equals("") || user.equalsIgnoreCase("guest")){
			error = "Please login first to rate the product";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp");
		}
		else{
			String productId = (String) request.getAttribute("productId");
			reviewService = new ReviewServiceImpl();
			try{
				list = reviewService.getProductReview(productId);
				
				System.out.println("List of Comments = "+list.size());
				request.setAttribute("Comments", list);
				//message = "Thanks for rating this product";
				//response.sendRedirect("/Git_Punjabi_Fashion/jsp/products/showProduct.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
	}

}
