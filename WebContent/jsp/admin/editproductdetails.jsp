<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="../../error.jsp?error=Login as admin to access this page" %>
    <%@ page import="java.util.List"  %>
    <%@ page import="java.util.Iterator"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punjabi fashion</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
 <script type="text/javascript" src="../../javascripts/addProduct.js"></script>

 <link rel="stylesheet" href="/Git_Punjabi_Fashion/index.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/admin/home.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/admin/addproduct.css">
  <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/admin/editproduct.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/admin/deleteproduct.css">  
 
</head>
<body >
<%@ include  file='/header.jsp' %>
<%
	if(session == null || session.getAttribute("session_user") == null){
		throw new Exception();
	}else if(session.getAttribute("session_user_role") == null || !session.getAttribute("session_user_role").toString().equalsIgnoreCase("admin")){
		throw new Exception();
	}
%>
	<div class="container">		
				<div class="menu-items">
						<li><a href="/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp" >Add A Product</a></li>
						<li><a href="/Git_Punjabi_Fashion/jsp/admin/editproduct.jsp" >Update Product Details</a></li>
						<li><a href="/Git_Punjabi_Fashion/jsp/admin/deleteproduct.jsp" >Remove Product From Market</a></li>
				</div>
	</div>	
		
		<div class="action-block">
			<div class="action-container">
			
				<div class="action-display" id="addproduct" style="display: block">
<%
	List<String[]> list = (List)request.getAttribute("productData");
	Iterator<String[]> itr = list.iterator();
	String[] product = itr.next();
%>


<div class="add-product" >	
	<div class='product-entry-header'>
        <div class='header-name'>Punjabi Fashion Products Inventory</div>
        <div class='header-action'>Update Product Details</div>
    </div>
    <hr>
    	
      <form action="${pageContext.request.contextPath}/EditProductDetails" method="post" id="editForm" >
      	
         <div class='form-group'>
         	<lable>Product Id  :  </lable><%=product[0] %>
        	<input class="form-control" name="id" type="hidden" value="<%=product[0] %>" >
        </div>
        <div class='form-group'>
        <lable>Product Name  :  </lable><%=product[1] %>
        	
        </div>
        <div class='form-group'>
        	<lable>Product Title  :  </lable><%=product[2] %>
        	
        </div>
        
        <div class='form-group'>
        	<lable>Old Product MRP  :  </lable><%=product[3] %>
        	<input class="form-control" name="productmrp"  type="text" placeholder="Enter New MRP" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01" title="This should be a number with up to 2 decimal places.">
        </div>
        <div class='form-group'>
        	<lable>Old Discount  :  </lable><%=product[4] %>
        	<input class="form-control" name="productdiscount"  type="text" placeholder="Enter New Discount" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01"  title="This should be a number with up to 2 decimal places.">
        </div>
       
		 
		  	
        <div class='form-group'>
        	
        			<button onclick="funEdit(<%=product[2]%>,<%=product[3]%>,<%=product[4]%>)" name="edit" class="btn btn-primary">Save</button>
        		
        	
        </div>
       </form>
       
</div>
</div>
			
				
				
			</div>
		</div>
		
	


</body>
</html>