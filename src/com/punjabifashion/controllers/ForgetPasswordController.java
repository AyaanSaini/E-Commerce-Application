package com.punjabifashion.controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.punjabifashion.service.UserService;
import com.punjabifashion.service.UserServiceImpl;

@WebServlet("/ForgetPassword")
public class ForgetPasswordController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	HttpSession session;
	String message;
	String error;
	UserService userService;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("-->ForgetPasswordController");
		String username = null;
		String q1 = null;
		String ans1 = null;
		String q2 = null;
		String ans2 = null;
		
		
		try{
			session = request.getSession();
		}catch(Exception e){
			message = "Somthing went wrong, Please contact admin";
			response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(message, "UTF-8"));
		}
		
		//if(session.getAttribute("session_user_role") != null) {
		//System.out.println("session user = "+session.getAttribute("session_user"));
		//System.out.println("session = "+session.getAttribute("session_user_role"));
		if((session.getAttribute("session_user") == null)){ 
			// change above line, remove (session.getAttribute("session_user_role") == null) &&
			System.out.println("inside session = null");
			username = request.getParameter("username");
			q1 = request.getParameter("question1");
			q2 = request.getParameter("question2");
			ans1 = request.getParameter("ans1");
			ans2 = request.getParameter("ans2");
			System.out.println(username+","+q1+","+q2+","+ans1+","+ans2);
			if(username ==null || q1 == null || ans1 == null || q2 == null || ans2 == null ) {
				error = "Please fill all fields";
				System.out.println("everything is null");
			}
			else {
				System.out.println("inside else");
				String[] res;
				userService = new UserServiceImpl();
				//System.out.println("response = "+userService.forgetPassword(username, q1, ans1, q2, ans2));
				res = userService.forgetPassword(username, q1, ans1, q2, ans2);
				System.out.println("response = "+res[0]);
				if(res[0].equals("fine")) {
					session.setAttribute("session_user_fp_username", username);
					session.setAttribute("session_user_fp_pwd", res[1]);
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/forget_change_password.jsp");
				}
				else if(res[0].equals("username")){
					error = "User does not Exist,Please provide correct username";
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/forget_password.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
				}else if(res[0].equals("question/answers")){
					error = "Either Questions or Answers are wrong";
					response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/forget_password.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
				}
				else {
					error = "Somthing went wrong, Please contact System Admin";
					response.sendRedirect("/Git_Punjabi_Fashion/error.jsp?error=" + URLEncoder.encode(error, "UTF-8"));
				}
				
			}
		}else {
			System.out.println("outside session");
		}
	}
}
