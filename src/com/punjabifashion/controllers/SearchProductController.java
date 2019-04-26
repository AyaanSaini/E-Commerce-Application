package com.punjabifashion.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.punjabifashion.service.ProductService;
import com.punjabifashion.service.ProductServiceImpl;

@WebServlet("/SearchProductsController")
public class SearchProductController extends HttpServlet{
	ProductService productService;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String message;
		List list ;
		try{
			productService = new ProductServiceImpl();
			list = productService.getProducts();
			System.out.println("List of Products = "+list);
			message = "Product List";
			request.setAttribute("filteredProducts", list);
			System.out.println("success == "+request.getAttribute("filteredProducts"));
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/jsp/products/main.jsp");
			rd.forward(request, response);
			
		}catch(Exception e){
			message = e.getMessage();
			request.setAttribute("failure", message);				
			e.printStackTrace();
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/jsp/products/main.jsp");
			rd.forward(request, response);
		}
	}

}
