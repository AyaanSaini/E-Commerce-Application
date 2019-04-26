<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<style type="text/css">
	.message{
		font-size:20px;
		color: green;
		font-weight: bolder;
		margin: auto;
	}
	.error{
		font-size:20px;
		color: red;
		font-weight: bolder;
		margin: auto;
	}
</style>
</head>
<body>
	<div class='message'>${param.message}</div>
    <div class='error'>${param.error}</div>
</body>
</html>