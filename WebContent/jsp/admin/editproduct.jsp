<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punjabi fashion</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
<script type="text/javascript" src="../../javascripts/editProduct.js"></script>

 <link rel="stylesheet" href="../../index.css">
 <link rel="stylesheet" href="../../css/admin/home.css">
<link rel="stylesheet" href="../../css/admin/addproduct.css">
 <link rel="stylesheet" href="../../css/admin/editproduct.css"> 
<link rel="stylesheet" href="../../css/admin/deleteproduct.css">
 
 
 
 
</head>
<body >
<%@ include  file='/header.jsp' %>

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



<div class="edit-product">
	<div class='product-edit-header'>
        <div class='header-name'>Punjabi Fashion Products Inventory</div>
        <div class='header-action'>Product Details Update</div>
    </div>
    <hr>
    <form action="${pageContext.request.contextPath}/Search" method="post">
    	<div class="row">
    		<div class="col-md-4">
    			<div class="form-group">
    				<input class="form-control" name="productId" placeholder="Enter Product Id">
    			</div>
    		</div>
    		<div class="col-md-1 label-or" >
    			<div class="form-group">
    				<label>--OR--</label>
    			</div>
    		</div>
    		<div class="col-md-4">
    			<div class="form-group">
    				<input class="form-control" name="productTitle" placeholder="Enter Product Title">
    			</div>
    		</div>
    		<div class="col-md-2" >
    			<div class="form-group">
    				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Search</button>
    			</div>
    		</div>
    	</div>
    </form>
    <div class="table-header">Find Below Products</div>
    <hr>
    <%if(session.getAttribute("successEdit")!=null){ 
   		List listOfProduct = (List)session.getAttribute("successEdit");
   		Iterator itr = listOfProduct.iterator();
    %>
    <div class="table-display">
	    <div class="products-display-table">
	    	
	    </div>
	    <form action="${pageContext.request.contextPath}/EditDetails" method="post">
	    <table class="table table-striped">
	    	<thead>
	    		<tr>
	    			<th>No.</th>
	    			<th>Product Id</th>
	    			<th>Product Name</th>
	    			<th>Product Title</th>
	    			<th>Product MRP</th>
	    			<th>Discount</th>
	    			<th>Action</th>
	    		</tr>
	    	</thead>
	    	<tbody>
	    	
	    	<%  int i=1;
	    		while(itr.hasNext()){
	    		String[] str = (String[])itr.next();	
	    		
	    	%>
	    		<tr>
	    			<td><%=i %></td>
	    			<td><%=str[0] %></td>
	    			<td><%=str[1] %></td>
	    			<td><%=str[2] %></td>
	    			<td><%=str[3] %></td>
	    			<td><%=str[4] %></td>
	    			<input type="hidden" value="<%=str[0] %>" name="productId">
	    			<td>
	    				<button type="submit" class="btn btn-primary btn-static" id="btn-static" >Edit</button>
	    			
	    			</td>
	    			
	    		</tr>
	    		<%i++;} %>
	    	</tbody>
	    </table>
	    </form>
	</div>
	<%}else if(request.getAttribute("failure") != null){%>
		<div class='error'><%=request.getAttribute("failure")%></div>
	<%}else{
		%>
		<div class='error'>Search Products above</div>
	<%
	}
    %>
	
</div>

<!-- Home below -->
</div>
			
				
				
			</div>
		</div>
		
	


</body>
</html>