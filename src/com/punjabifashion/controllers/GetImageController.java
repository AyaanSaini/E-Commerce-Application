package com.punjabifashion.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.dao.ProductDAO;
import com.punjabifashion.dao.ProductDAOImpl;

@WebServlet("/GetImage")
public class GetImageController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ProductDAO productDAO;
	HttpSession session;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		try{
			session  = request.getSession();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String id = (String)request.getParameter("imageId");
		System.out.println("Image id = "+id);
		int productId = Integer.parseInt(id);
		try{
			productDAO = new ProductDAOImpl();
			byte[] imageData = productDAO.getImage(productId);
			response.setContentType("image/jpeg");
			response.getOutputStream().write(imageData);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			response.getOutputStream().flush();
	
		}
	}
}
