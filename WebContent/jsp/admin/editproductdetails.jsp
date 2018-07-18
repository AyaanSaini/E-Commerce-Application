<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<%
	List<String[]> list = (List)request.getAttribute("productData");
	Iterator<String[]> itr = list.iterator();
%>


<div class="add-product" >	
	<div class='product-entry-header'>
        <div class='header-name'>Punjabi Fashion Products Inventory</div>
        <div class='header-action'>Product Entry</div>
    </div>
    <hr>
    	
      <form action="${pageContext.request.contextPath}/EditProductDetails" method="post" >
      	
         <div class='form-group'>
        	<%-- <input class="form-control" name="id" type="text" value="<%=itr.next()[0] %>"  disabled="disabled"> --%>
        </div>
        <div class='form-group'>
        	<%-- <input class="form-control" name="name" type="text" value="<%=itr.next()[1] %>" disabled="disabled"> --%>
        </div>
        <div class='form-group'>
        	<input class="form-control" name="title" type="text" value="<%=itr.next()[2] %>"  disabled="disabled">
        </div>
        
        <div class='form-group'>
        	<input class="form-control" name="price"  type="text" placeholder="Enter MRP" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01" title="This should be a number with up to 2 decimal places.">
        </div>
        <div class='form-group'>
        	<input class="form-control" name="discount"  type="text" placeholder="Enter Discount" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01"  title="This should be a number with up to 2 decimal places.">
        </div>
       
		 
		  	<div class='message'>${param.message}</div>
    		<div class='error'>${param.error}</div>
        <div class='form-group'>
        	
        			<button type="submit" name="edit" class="btn btn-primary">Save</button>
        		
        	
        </div>
       </form>
       
</div>
</div>
			
				
				
			</div>
		</div>
		
	


</body>
</html>