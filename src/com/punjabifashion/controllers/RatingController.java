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

@WebServlet("/AddRating")
public class RatingController extends HttpServlet {

	HttpSession session;
	String error;
	String message;
	double rating;
	int productId;
	RatingService ratingService;
	int res=0;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
			String rate = request.getParameter("rating");
			if(rate.equals("") || rate.equals(" "))
				rate = "0";
			 rating = Double.parseDouble(rate);
			 System.out.println("User rating = "+rating);
			 productId = Integer.parseInt(request.getParameter("productId"));
			 ratingService = new RatingServiceImpl();
			 try {
				res = ratingService.addRating(productId,rating,user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 if(res == 1){
				 message = "Thanks For Rating the Product, Rating submitted successfully";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&ratingMessage="+URLEncoder.encode(message, "UTF-8"));
				 rd.forward(request, response);
			 } else if(res == 2){
				 error = "Sorry! You have already rated this product.";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&ratingError="+URLEncoder.encode(error, "UTF-8"));
				 rd.forward(request, response);
			 }
			 else{
				 error = "Sorry! Some Problem To Submit The Rating.";
				 RequestDispatcher rd = request.getRequestDispatcher("ProductDetails?id ="+productId+"&ratingError="+URLEncoder.encode(error, "UTF-8"));
				 rd.forward(request, response);
			 }
		}
	}
	
}
