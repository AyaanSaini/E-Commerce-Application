<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="../../error.jsp?error=Somthing went wrong, Please contact System Administrator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="../../css/login/signup.css">
  <script type="text/javascript" src= "../../javascripts/forgetChangePassword.js"></script>
</head>
<body>
<div class='container'>
<div class='signup-container'>
<!-- <div class="close"><a href="/Git_Punjabi_Fashion/"><span class="glyphicon glyphicon-remove-sign"></span></a></div> -->

  <div class='header'>
    <div class='header-name'>Punjabi Fashion</div>
    <div class='header-action'>Change Password</div>
  </div>
  <hr>
  
  <form action="${pageContext.request.contextPath}/ChangePassword" method="post" id="changePwd">
    <div class='note'>**All the fields are mendatory**</div>
    <div class='form-group'>
      <input type='text' disabled class='form-control' id='username' placeholder="Enter username" name='username' value="<%=session.getAttribute("session_user") %>" required>
    </div>
    <div class='form-group'>
        <input type="password" class="form-control" id='oldpwd' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="Enter Old password" name="oldpwd" required>
    </div>
    <div class='form-group'>
        <input type="password" class="form-control" id='pwd' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="Enter New password" name="newpwd" required>
    </div>
    <div class='form-group'>
        <input type="password" class="form-control" id='cnfpwd' pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" placeholder="Confirm New password" name="cnfnewpwd" required>
    </div>
  
    <div class='message' id="msg">${param.message}</div>
    <div class='error' id="err">${param.error}</div>
    <div class='form-display'>
    	<div class="row">
    		<div class="col-md-6">
    			<button type="button" class="btn btn-primary" onclick="validateUser('changePwd')" >Change Password</button>
    		</div>
    		<div class="col-md-6">
    			<button type="reset" class="btn btn-primary" >Cancel</button>
    		</div>
    	</div>
    </div>
    
  </form>
	<div class="link-center">
		<a href='/Git_Punjabi_Fashion/jsp/login/login.jsp'><span class="glyphicon glyphicon-arrow-left"> Back to login</span></a>
	</div>
</div>

</div>
</body>
</html>