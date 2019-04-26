package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.service.RatingService;
import com.punjabifashion.service.RatingServiceImpl;
import com.punjabifashion.service.ReviewService;
import com.punjabifashion.service.ReviewServiceImpl;
import com.punjabifashion.validators.ReviewValidation;

@WebServlet("/AddComment")
public class ReviewController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String error;
	String message;
	String comment;
	int productId;
	ReviewService reviewService;
	int res=0;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		comment = null;
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
			 comment = request.getParameter("addComment");
			 System.out.println("User rating = "+comment+"  Product Id = "+productId);
			 productId = Integer.parseInt(request.getParameter("productId"));
			 reviewService = new ReviewServiceImpl();
			 ReviewValidation rv = new ReviewValidation();
			 if(rv.reviewValidation(comment,productId,user))
				try {
					res = reviewService.addComment(comment,productId,user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			else {
				 message = "Please provide some comments";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&reviewError="+URLEncoder.encode(error, "UTF-8"));
				 rd.forward(request, response);
			 }
			 if(res == 1){
				 message = "Thanks For Providing Review";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&reviewMessage="+URLEncoder.encode(message, "UTF-8"));
				 rd.forward(request, response);
			 }
			 else if(res == 2){
				 error = "Sorry! You have already submitted review for this product.";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&reviewError="+URLEncoder.encode(error, "UTF-8"));
				 rd.forward(request, response);
			 }
			 else{
				 error = "Sorry! Some Problem To Submit Review.";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&reviewError="+URLEncoder.encode(error, "UTF-8"));
				 rd.forward(request, response);
			 }
		}
	}
}
