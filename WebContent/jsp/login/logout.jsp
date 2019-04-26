<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="../../error.jsp?error=Somthing went wrong, Please contact System Administrator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String username = null;
	String fwdpage = request.getParameter("page");
	if(session.getAttribute("session_user_role") != null){
		session.setAttribute("session_user_role",null);
	}
	//add highlighted if condition in logout.jsp
	if(fwdpage != null && fwdpage.equalsIgnoreCase("changePwd")){
		session.setAttribute("session_user",null);
		response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/login.jsp");
	}
	else if(session != null && session.getAttribute("session_user") != null){
		//username = session.getAttribute("session_user").toString();
		session.setAttribute("session_user",null);
		response.sendRedirect("../../index.jsp");
	}
	else{
		session.setAttribute("session_user",null);
		response.sendRedirect("../../index.jsp");
	}
	 /* if(username == null||username.equals("")){
		response.sendRedirect("/Git_Punjabi_Fashion/jsp/login/login.jsp");
	}
	else{
		session.setAttribute("session_user",null);
		response.sendRedirect("../../index.jsp");
	}  */
%>
</body>
</html>