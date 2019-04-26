package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.beans.User;
import com.punjabifashion.service.UserService;
import com.punjabifashion.service.UserServiceImpl;

@WebServlet("/ChangePassword")
public class ChangePasswordController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession session;
	String message;
	String error;
	UserService userService;
	User user;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("-->ChangePasswordController");
		try{
			//System.out.println("0");
			session = request.getSession();
		}catch(Exception e){
			//System.out.println("0.1");
			message = "Session is null,Please contact system admin";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		String sessionUsername = null;		
		String sessionPassword = null;
		//System.out.println("0.11");
		if( session.getAttribute("session_user_fp_username")!= null) {
			//System.out.println("1");
			sessionUsername = (String)session.getAttribute("session_user_fp_username");
			sessionPassword = (String)request.getParameter("newpwd");
			//System.out.println(sessionUsername+"||||"+sessionPassword);
		}
		else if(session.getAttribute("session_user") !=null) {
			//System.out.println("2");
			sessionUsername = (String)session.getAttribute("session_user");
			sessionPassword = (String)request.getParameter("newpwd");
			//System.out.println(sessionUsername+"||||"+sessionPassword);
		}else {
			//System.out.println("3");
			message = "Sorry!!! You are trying to breakin into system.";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));		
		}
		if(sessionUsername != null && sessionPassword != null) {
			//System.out.println("inside if condition");
			userService = new UserServiceImpl();
			user = new User();
			user.setUsername(sessionUsername);
			user.setPassword(sessionPassword);
			int res = userService.updateUser(user);
			System.out.println("res = "+res);
			if(res == 1) {
				System.out.println("4");
				message = "Password change successfully";
				response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/logout.jsp?page=changePwd");
			}
			else if(res == 100){
				//System.out.println("5");
				message = "Password Not changed, Please try again after some time";
				response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/logout.jsp");
			}
			else {
				//System.out.println("6");
				message = "Please contact system admin";
				response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
			}
		}
		else {
			System.out.println("username  or password is null");
		}
	}
}
