<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
 <link rel="stylesheet" href="../../css/admin/editproduct.css">
 <link rel="stylesheet" href="../../css/admin/deleteproduct.css">
 <script type="text/javascript">
 $(document).on('click', '.browse', function(){
	  var file = $(this).parent().parent().parent().find('.file');
	  file.trigger('click');
	});
	$(document).on('change', '.file', function(){
	  $(this).parent().find('.form-control').val($(this).val().replace(/C:\\fakepath\\/i, ''));
	});
 </script>
 
 <script type="text/javascript">
 	function addProduct(){
 		
 		$("div.add-div").show("slow"); 	    
 	    $("div.edit-div").hide("slow");
 	    $("div.delete-div").hide("slow");
 	  
 	}
 	
 	function editProduct(){
 		
 		$("div.add-div").hide("slow"); 	    
 	    $("div.edit-div").show("slow");
 	    $("div.delete-div").hide("slow");
 	}
 	
 	function deleteProduct(){
 		
 		$("div.add-div").hide("slow"); 	    
 	    $("div.edit-div").hide("slow");
 	    $("div.delete-div").show("slow");
 	}
 </script>
 
 
 
 
</head>
<body >
<%@ include  file='/header.jsp' %>

	<div class="container">		
				<div class="menu-items">
						<li><a href="javascript:void(0)" onclick = "javascript:addProduct()">Add A Product</a></li>
						<li><a href="javascript:void(0)" onclick = "javascript:editProduct()">Update Product Details</a></li>
						<li><a href="javascript:void(0)" onclick = "javascript:deleteProduct()">Remove Product From Market</a></li>
				</div>
	</div>	
		
		<div class="action-block">
			<div class="action-container">
			
				<div class="action-display" id="addproduct" style="display: block">
					
				</div>
			
				
				
			</div>
		</div>
		
	


</body>
</html>