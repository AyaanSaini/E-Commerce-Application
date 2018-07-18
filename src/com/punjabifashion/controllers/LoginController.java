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

import com.punjabifashion.service.UserService;
import com.punjabifashion.service.UserServiceImpl;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	String message;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		session = request.getSession();
	      if(session.getAttribute("session_user") == null || session.getAttribute("session_user").equals("")){
	    	  String username = (String) request.getParameter("username");
	    	  String pwd = (String) request.getParameter("pwd");
	    	  if(username.equals("")||username == null||username.contains("<")){
	    		  System.out.println("username is not in correct format");
	    		  message="Please provide correct username";
	    		  response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/login.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
	    	  }
	    	  else if(pwd == null || pwd.equals("")){
	    		  System.out.println("password is not in correct format");
	    		  message="Please provide correct password";
	    		  response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/login.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
	    	  }
	    	  else{
	    		  UserService userService = new UserServiceImpl();
	    		  String userRole = null;
	    		  try {
					userRole =  userService.isValidUser(username, pwd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		  
	    		  
	    		  if(userRole.equals("customer")){
	    			  System.out.println("user is recorded in DB");
	    			  session.setAttribute("session_user", username);
	    			  session.setAttribute("session_user_role", userRole);
	    			  response.sendRedirect("/Git_Punjabi_Fashion/index.jsp");
	    		  }
	    		  else if(userRole.equals("admin")){
	    			  System.out.println("user is recorded in DB as Admin");
	    			  session.setAttribute("session_user", username);
	    			  session.setAttribute("session_user_role", userRole);
	    			  response.sendRedirect("/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp");
	    		  }
	    		  else{
	    			  System.out.println("user is not recorded in DB");
	    			  message="Either username or password is incorrect";
		    		  response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/login.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
	    		  }
	    	  }
	      }
	      else{
	    	  System.out.println("User already logged in");
	    	  response.sendRedirect("/Git_Punjabi_Fashion/index.jsp");
	      }
	}

}
