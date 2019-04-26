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

import com.punjabifashion.service.ProductService;
import com.punjabifashion.service.ProductServiceImpl;
import com.punjabifashion.service.RatingService;
import com.punjabifashion.service.RatingServiceImpl;
import com.punjabifashion.service.ReviewService;
import com.punjabifashion.service.ReviewServiceImpl;

@WebServlet("/ProductDetails")
public class GetProductDetailsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductService productService;
	RatingService ratingService;
	ReviewService reviewService;
	HttpSession session;
	String message;
	String error;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try{
			session = request.getSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		if(request.getAttribute("product") != null){
			request.setAttribute("product", null);
		}
		System.out.println("Product Id = "+request.getParameter("productId"));
		String productId = request.getParameter("productId");
		ratingService = new RatingServiceImpl();
		reviewService = new ReviewServiceImpl();
		productService = new ProductServiceImpl();
		String[] productDetails = new String[10];
		String[] product = null;
		String sizes = null;
		List<String[]> listOfReviews = null;
		double avgRating = 0;
		
		try {
			product = productService.getProduct(productId);
			sizes = productService.getProductSizes(Integer.parseInt(productId));
			avgRating = ratingService.getProductAvgRating(productId);
			listOfReviews = reviewService.getProductReview(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Session.attribute = "+session.getAttribute("session_user"));
		System.out.println("AvG Rating = "+avgRating);
		product[5] = avgRating+"";
		
		if(session.getAttribute("session_user") == null){			
			request.setAttribute("product", product);
			request.setAttribute("productSizes", sizes);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/jsp/products/showProduct.jsp");
			rd.forward(request, response);
		}else{		
			request.setAttribute("product", product);
			request.setAttribute("productSizes", sizes);
			System.out.println("product sizes = "+request.getAttribute("productSizes"));
			request.setAttribute("Comments", listOfReviews);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/jsp/products/showProduct.jsp");
			rd.forward(request, response);
		}
	}
}
