<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page errorPage="../../error.jsp?error=Login as admin to access this page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>punjabi fashion</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/css/bootstrap-multiselect.css">
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js"></script>
 
 <script type="text/javascript" src="../../javascripts/addProduct.js"></script>

 <link rel="stylesheet" href="../../index.css">
 <link rel="stylesheet" href="../../css/admin/home.css">
 <link rel="stylesheet" href="../../css/admin/addproduct.css">
<!--  <link rel="stylesheet" href="../../css/admin/editproduct.css">
 <link rel="stylesheet" href="../../css/admin/deleteproduct.css"> -->
 <script type="text/javascript">
 $(document).on('click', '.browse', function(){
	  var file = $(this).parent().parent().parent().find('.file');
	  file.trigger('click');
	});
	$(document).on('change', '.file', function(){
	  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
	});
 </script>
 
 
 
 
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
	String[] menCategory = {"Clothing","Footwear","Jewellery"};
	String[] womenCategory = {"Clothing","Footwear","Jewellery","Gota Patti","Bangles"};
	String[] kidsCategory = {"Clothing","Footwear","Jewellery","Bangles"};
	
	
	String[] menClothingFit = {"Slim Fit","Skiny Fit","Regular Fit"};
	String[] womenClothingFit = {"Slim Fit","Skiny Fit","Regular Fit"};
	String[] kidsClothingFit = {""};
	
	String[] clothingSizes = {"XXS","XS","S","M","L","XL","XXL","XXXL"};
	
	String[] kidsClothingSizes = {"New Born","4","6","8","10","12","14","16"};
	
	String[] footwearSizes = {"4","5","5.5","6","6.5","7","8","9","10","10.5","11","12"};
	String[] kidsFootWearSizes = new String[24];
	int k=16;
	for(int i=0;i<24;i++){
		kidsFootWearSizes[i] = ""+(k++);
	}
	
	String[] colors = {"beige","black","blue","bronze","brown","burgundy","charcol","coffee brown","coral","cream","fluorescent","gold","gray","green","khaki","lime","magenta","maroon","mauve","multi","mustard","navy","off white","olive","orange","peach","pink","puple","red","rose","rust","saffron","silver","steal","taupe","teal","torquoise","white","yellow"};
	
%>


<div class="add-product" >	
	<div class='product-entry-header'>
        <div class='header-name'>Punjabi Fashion Products Inventory</div>
        <div class='header-action'>Product Entry</div>
    </div>
    <hr>
      <form action="${pageContext.request.contextPath}/AddProduct" method="post" enctype="multipart/form-data" id="addProductForm">
      	<div class="form-group">   
		      <div class="row">
		      		<div class="col-md-4 check-gender">     
				      	<div class="radio">
					      <label><input type="radio" id="gender" name="gender" value="men" onclick="genderFun('men')">Men</label>
					    </div>
				   	</div>
				   <div class="col-md-4">
					    <div class="radio">
					      <label><input type="radio" id="gender" name="gender" value="women" onclick="genderFun('women')">Women</label>
					    </div>
				    </div>
				    <div class="col-md-4">
					    <div class="radio">
					      <label><input type="radio" id="gender" name="gender" value="kids" onclick="genderFun('kids')">Kids</label>
					    </div>
				    </div>
			    </div>
			  </div>
        
        <div class='form-group'>
        	<select class="form-control" name="category" id="category" onchange="categoryFun()" required>
        		<option disabled>Select A Category</option>
        		<option id='all' value="clothing">Clothing</option>
        		<option id="all" value="footwear">Footwear</option>
        		<option id="all" value="jewellary">Jewellary</option>
        		<option id="women" value="gottaPatti">Gotta patti</option>
        		<option id="women" value="bangles" >Bangles</option>
        	</select>
        </div>
        
        <div class='form-group stitched-div' id = "stitched">
        	<select class="form-control" name="stitched" id="sel-stitched" onchange="stitchedFun()">
        		<option disabled>Select Weather Product Is Stitched Or Not</option>
        		<option value="true">Yes</option>
        		<option value="false">No</option>        		
        	</select>
        </div>
        
        <div class='form-group fit-div' id = "fitdiv">
        	<select class="form-control" name="fit" >
        		<option disabled>Select A Fitting</option>
        		<option value="slim">Slim Fit</option>
        		<option value="skiny">Skiny Fit</option>
        		<option value="regular">Regular Fit</option>
        	</select>
        </div>
        <div class='form-group color-div' id="color">
        	<select class="form-control sizes"  name="color" required >
        	<option disabled>Select A Color</option>
        	<%for(String color:colors){ %>
        		<option value="<%=color%>"><%=color%></option>
        	<% }%>
        	</select>
        </div>
        <!-- Men/Women Clothing Sizes -->
        <div class='form-group sizes-div' id="clothing-sizes" >
        	<div class="row">
        		<div class="col-md-7">Select All The Sizes For This Product</div>
        		<div class="col-md-5">
        			<select class="form-control sizes" name="size[]"  multiple = "multiple" id="size1" >
		        		<option disabled>Select sizes</option>
		        		 <%for(String size:clothingSizes){ %>
		        			<option value="<%=size%>"><%=size%></option>
		        		 <% }%>  		
		        	</select>
        		</div>
        	</div>
        	
        </div>
        
        <!-- Kids Clothing Sizes -->
         <div class='form-group sizes-div' id="kids-clothing-sizes" >
         <div class="row">
        		<div class="col-md-7">Select All The Sizes For This Product</div>
        		<div class="col-md-5">
        			<select class="form-control sizes" name="size[]"  multiple = "multiple" id="size2" >
		        		<option disabled>Select sizes</option>
		        		 <%for(String size:kidsClothingSizes){ %>
		        			<option value="<%=size%>"><%=size%></option>
		        		  <% }%>  		
		        	</select>
        		</div>
        	</div>
        	
        </div>
        
        <!-- Men/Women Footwear Sizes -->
         <div class='form-group sizes-div' id="footwear-sizes" >
         <div class="row">
        		<div class="col-md-7">Select All The Sizes For This Product</div>
        		<div class="col-md-5">
        			<select class="form-control sizes" name="size[]"  multiple = "multiple" id="size3" >
		        		<option disabled>Select sizes</option>
		        		 <%for(String size:footwearSizes){ %>
		        			<option value="<%=size%>"><%=size%></option>
		        		  <% }%>  		
		        	</select>
        		</div>
        	</div>
        	
        </div>
        
        <!-- Kids Footwear Sizes -->
         <div class='form-group sizes-div' id="kids-footwear-sizes" >
         <div class="row">
        		<div class="col-md-7">Select All The Sizes For This Product</div>
        		<div class="col-md-5">
        			<select class="form-control sizes" name="size[]"  multiple = "multiple" id="size4" >
		        		<option disabled>Select sizes</option>
		        		 <%for(String size:kidsFootWearSizes){ %>
		        			<option value="<%=size%>"><%=size%></option>
		        		 <% }%>  		
        			</select>
        		</div>
        	</div>
        	
        </div>
        
        
        
        
        
        <div class='form-group'>
        	<input class="form-control" name="name" type="text" placeholder="Enter Product Name" required>
        </div>
        <div class='form-group'>
        	<textarea class="form-control" name="description" placeholder=" Enter Product Description" row="4" column="250" required></textarea>
        </div>
        <div class='form-group'>
        	<input class="form-control" name="price" type="text" placeholder="Enter MRP" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01" title="This should be a number with up to 2 decimal places.">
        </div>
        <div class='form-group'>
        	<input class="form-control" name="discount" type="text" placeholder="Enter Discount" required pattern="[0-9]+([\.,][0-9]+)?" step="0.01"  title="This should be a number with up to 2 decimal places.">
        </div>
        <div class="form-group">
		    <input type="file" name="img[]" class="file" required>
		    <div class="input-group col-xs-12">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-picture"></i></span>
		      <input id="photoPathDisplay" type="text" class="form-control input-lg" disabled placeholder="Upload Image">
		      <span class="input-group-btn">
		        <button class="browse btn btn-primary input-lg" type="button"><i class="glyphicon glyphicon-search"></i> Browse</button>
		      </span>
		    </div>
		  </div>
		  <div class="form-group"><label>Maximum file size should be 10MB*</label></div>
		  	<div class='message'>${param.message}</div>
    		<div class='error'>${param.error}</div>
        <div class='form-group'>
        	<div class="row">
        		<div class="col-md-3"></div>
        		<div class="col-md-3">
        			<button onclick="addProductSubmit()" name="add" class="btn btn-primary">Add Product</button>
        		</div>
        		<div class="col-md-3">
        			<button type="reset" name="reset" class="btn btn-warning">Reset</button>
        		</div>
        		<div class="col-md-3"></div>
        	</div>
        	
        </div>
       </form>
       
</div>
</div>
			
				
				
			</div>
		</div>
		
	


</body>
</html>