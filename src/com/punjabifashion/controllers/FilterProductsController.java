package com.punjabifashion.controllers;

import java.io.IOException;
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

@WebServlet("/FilterProducts")
public class FilterProductsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductService productService;
	HttpSession session;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("-->FilterproductsController");
		try {
			session = request.getSession();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String genderFilter = request.getParameter("genderHidden");
		if(genderFilter!=null && !genderFilter.equals("")) 
			session.setAttribute("genderFilter", genderFilter);
		else if(session.getAttribute("genderFilter") !=null) 
			genderFilter = (String) session.getAttribute("genderFilter");		
		if(request.getAttribute("genderAttribute") == null || (!request.getAttribute("genderAttribute").equals(genderFilter)))
			request.setAttribute("genderAttribute", genderFilter);
		
		
		String categoryFilter = request.getParameter("categoryHidden");
		if(categoryFilter!=null && !categoryFilter.equals("")) 
			session.setAttribute("categoryFilter", categoryFilter);
		else if(session.getAttribute("categoryFilter") !=null) 
			categoryFilter = (String) session.getAttribute("categoryFilter");		
		if(request.getAttribute("categoryAttribute") == null || (!request.getAttribute("categoryAttribute").equals(categoryFilter)))
			request.setAttribute("categoryAttribute", categoryFilter);
		
		
		String sizesFilter = request.getParameter("sizesHidden");		
		if(sizesFilter!=null && !sizesFilter.equals("")) 
			session.setAttribute("sizesFilter", sizesFilter);
		else if(session.getAttribute("sizesFilter") !=null) 
			sizesFilter = (String) session.getAttribute("sizesFilter");	
		if(request.getAttribute("sizesAttribute") == null || (!request.getAttribute("sizesAttribute").equals(sizesFilter)))
			request.setAttribute("sizesAttribute", sizesFilter);
		
		String titleFilter = request.getParameter("titleLike");
		if(titleFilter!=null && !titleFilter.equals("")) {
			session.setAttribute("titleFilter", titleFilter);
			session.setAttribute("sizesFilter", "");
			session.setAttribute("categoryFilter", "");
			session.setAttribute("genderFilter", "");
		}
		else if(session.getAttribute("titleFilter") !=null) 
			titleFilter = (String) session.getAttribute("titleFilter");	
		if(request.getAttribute("titleAttribute") == null || (!request.getAttribute("titleAttribute").equals(titleFilter)))
			request.setAttribute("titleAttribute", titleFilter);		
		if(titleFilter == null)
			titleFilter = "";
		
		
		String[] sizes = new String[20];
		if(sizesFilter != null)
		sizes = sizesFilter.split(",");
		
		
		
		String message;
		List list ;
		try{
			productService = new ProductServiceImpl();
			list = productService.getFilteredProducts(titleFilter,genderFilter,categoryFilter,sizes);
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
