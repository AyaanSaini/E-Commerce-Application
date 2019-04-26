<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page errorPage="../../error.jsp?error=Somthing went wrong, Please contact System Administrator" %>
<%@ page import="java.util.List"  %>
<%@ page import="java.util.Iterator"  %>
<%@ page import="java.io.BufferedInputStream"  %>
<%@ page import="java.io.BufferedOutputStream"  %>
<%@ page import="java.io.FileOutputStream"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Punjabi Fashion</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/products/main.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/products/display-products.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/products/filter-products.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/index.css">
 
 
 <link href="/Git_Punjabi_Fashion/css/star-rating.css" media="all" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/Git_Punjabi_Fashion/javascripts/star-rating.js" type="text/javascript"></script>
<script src="/Git_Punjabi_Fashion/javascripts/review.js" type="text/javascript"></script>
 <script>
    $(document).on('ready', function () {
        $('.kv-gly-star').rating({
            containerClass: 'is-star'
        });
        
    });
</script>
 <style type="text/css">
 	img{
 		background-image: url('/Git_Punjabi_Fashion/images/temp/noImage.png');
 	}
 	.show-product-container{
 		margin-top:2cm;
 		margin-left:.5cm;
 		margin-right:1cm;
 	}
 	.display-comments{
 		border-right: 1px solid grey;
 		padding-left: 1cm;
 		padding-right: 2cm;
 	}
 	.comments{
 		height: 7.3cm;
 		width:100%;
 		overflow: scroll;
 	}
 	.comment{
 		border:1px solid grey;
 		border-radius: 15px;
 		margin-top: 10px;
 		padding: 10px;
 	}
 	.comment-username{
 		font-size: 14px;
 		font-weight: bold;
 	}
 	.comment-text-area{
 		margin-top:.2cm;
 		 
 	}
 	.product-detail-description-title{
 		font-size: 18px;
 		font-weight: bold;
 	}
 	.submit-btn{
 		margin-top: .2cm;
 	}
 	.image{
 		margin: auto;
 	}
 	.warning > p{
 		color: orange;
 		font-size: 16px;
 	}
 	.output{
 		text-align: center;
 		color: navy;
 	}
 	.product-discount{
 		color: green;
 	}
 	.comments-warning{
 		color: red;
 	}
 	.message{
 		color:green;
 	}
 	.error{
 		color:red;
 	}
 	.product-sizes{
 		font-size: 20px;
 	}
 	.display-sizes{
 		font-size: 15px;
 		margin: auto;
 	}
 	.display-size{
 		text-align:center;
 		width:35px;
 		border: 1px solid grey;	
 		display: inline-block;
 	}
 	.product-stars{
 		display:block;
 		height:21px;
 		overflow: hidden;
 	}
 	.product-star{
 		font-size:20px;
 		color:#f2cf21;
 		display: inline;
 		
 	}
 	.name{
 		font-size:20px;
 	}
 	.image-pos{
 		margin: auto;
 	}
 	.product-reviews{
 		font-size:16px;
 	}
 	.product-reviews-msg{
 		font-size: 14px;
 		color: orange;
 	}
 </style>
</head>
<body>
<%!String productId; %>
	<%@ include file="../../header.jsp" %>
	<div class="show-product-container">
	<div class='row'>
    <div class='product-display'>
        <div class='row'>
        <%
        String[] product = (String[])request.getAttribute("product");
    	if(product != null){
    		productId = product[0];
        %>        
            <div class='product-detail-section'>
                <div class='col-md-4'>
                    <div class='product-detail-image'>
                    	<img class="image-pos" src="${pageContext.request.contextPath}/GetImage?imageId=<%=productId %>" width='100%' height='500px'/>
                        
                    </div>
                </div>
                <div class='col-md-8'>
                    <div class='product-detail-description'>
                        <div class='product-detail-description-title'><%=product[1].toUpperCase() %></div>
                        <div class='product-detail-description-rating'>
                        	<% double rate = Math.round(Double.parseDouble(product[5])*10.0)/10.0;
                        		if(rate>0.0){
                        	%>                       	
	                        	<span class='product-stars' style="width: <%=25*rate %>px">
					               	<span class='glyphicon glyphicon-star product-star'></span>
					            	<span class='glyphicon glyphicon-star product-star'></span>
					                <span class='glyphicon glyphicon-star product-star'></span>
					                <span class='glyphicon glyphicon-star product-star'></span>
					                <span class='glyphicon glyphicon-star product-star'></span>
					            </span> 
	                            <span class='product-reviews'>(<%=rate %>)</span>
                            <%}else{ %>
                            	<span class='product-reviews-msg'>Be the first to rate this product.</span>
                            <%} %>
                        </div>
                        <hr>
                        <div class='product-detail-description-price'>
                        <%
                        	double discount = Double.parseDouble(product[4]);
                        	double mrp = Double.parseDouble(product[3]);
                        	double currPrice = mrp - (mrp*discount)/100;
                        	double saving = mrp - currPrice;
                        %>                        
                            
                            <%if(discount > 0.0){ %>
	                            <div class='product-mrp'>MRP: <del> $<%=product[3] %></del> </div>
	                            <div class='product-price'>Current Price : &nbsp;&nbsp;$<%=Math.round(currPrice*10.0)/10.0 %></div>                                                       
	                            <div class='product-discount'>You Save : &nbsp;&nbsp;$<%=Math.round(saving*10.0)/10.0 %> (<%=product[4] %>%)</div>
                        	<%} else{%>
                        		<div class='product-price'>Current Price : &nbsp;&nbsp;$<%=Math.round(currPrice*10.0)/10.0 %></div>
                        	<%} %>
                        </div>
                        <% 
                        	String[] sizes = request.getAttribute("productSizes").toString().split(",");
                        	if(!sizes[0].equalsIgnoreCase("free")){
                        %>
                        <div class="product-sizes">Sizes Available
                        <ul class="display-sizes">
                        	<%
                           		for(int i=0;i<sizes.length;i++){
                        	%>
                        		<li class="display-size">
                        			<%=sizes[i]%>
                        		</li>
                        	<%
                        		}
                        	%>
                        	</ul>
                        </div>
                        <br>
                        <%} %>
                        <div class='product-detail-description-productDescription'>
                        	<div class="product-detail-description-title">Description</div>
                            <p class='description'><%=product[2] %> </p>
                        </div>
                        
                    </div>
                </div>
            </div>
            <%	
    		}else{ %>
    			<div class="output">
    			<p>Sorry!!!</p>
    			<P>Product not exist temporarily OR Not able to load product details currently </P>
    			</div>
    		<%}
    		%>      
    </div>
    </div>
    </div>
<hr>
<%
	if(session.getAttribute("session_user") == null || session.getAttribute("session_user").toString().equalsIgnoreCase("guest")){
		%><div class="warning">
		<P>Please login to read/add your reviews</P>
		</div>
	<%}
	else{
%>
    <div class='product-detail-description-review'>
             
			<div class='row'>
					
			<!-- Comment Section -->		
					
					<div class="col-md-6">
				        	
				       
				<!-- display comments section -->
					
				                <div class='product-comment-section'>
				                    <h1>Customers Reviews</h1>              
				                    <%
				                    	List<String[]> list = (List<String[]>)request.getAttribute("Comments");
				                    	Iterator itr = null;
				                    	if(list != null){
                                    		itr = list.iterator();					                    	
                                    		if(!itr.hasNext()){
                                    		
				                    	
				                    %>
				                    <div class="comments-warning">
				                    	Be the first to review the product.
				                    </div>
				                    <%}else{ %>
				                    <div class='display-comments'>
				                            <div class='comments'>
				                            <% 
				                            	while(itr.hasNext()){
				                            		String[] comment = (String[])itr.next();
				                            		%>
				                                    <div class='comment'>
				                                        <div class='comment-username'>
				                                            <div class='name'><%=comment[1] %></div>
				                                           
				                                           <% double userRating = Math.round(Double.parseDouble(comment[3])*10.0)/10.0;%>
                        									
								                        	<span class='product-stars' style="width: <%=25*userRating %>px">
												               	<span class='glyphicon glyphicon-star product-star'></span>
												            	<span class='glyphicon glyphicon-star product-star'></span>
												                <span class='glyphicon glyphicon-star product-star'></span>
												                <span class='glyphicon glyphicon-star product-star'></span>
												                <span class='glyphicon glyphicon-star product-star'></span>
												            </span> 
								                            
				                                        </div>
				                                        <div class='comment-comment'>
				                                            <p class='comment-comments'><%=comment[2]%></p>
				                                        </div>
				                                    </div>
				                            	<% } %>
				                                </div>
				                    </div>
				                    <%} }%>
				            </div>
				        </div>
				        
				  <!-- Rating Section -->     
				        
				<div class="col-md-6">
				<h1>Review This Product</h1>
					<div class="rating-section">
						<div class='message'>${param.ratingMessage}</div>
    					<div class='error'>${param.ratingError}</div>
					<form action="${pageContext.request.contextPath}/AddRating" method="get">
						<input type="hidden" name="productId" value="<%=productId%>">
							<input name="rating" id="rating" type="text" class="rating rating-loading"  data-size="md" title="">
							<span><button type="submit" class="btn btn-primary submit-btn" >Add Rating</button></span>					
					</form>
					</div>
					
					<hr>
					<div class='product-comment-section'>
				                    <div class='comment-title'>Add Comment</div>
				                    <div class='message' id="msg">${param.reviewMessage}</div>
    								<div class='error' id="err">${param.reviewError}</div>
				                    <form action = "${pageContext.request.contextPath}/AddComment" method="get" id="commentSubmit">
					                    <input type="hidden" name="productId" value="<%=productId%>">
					                    <div class="comment-text-area">
					                    	<textarea name="addComment" id="comment" rows="4" cols="90" placeholder="Enter your Comments"></textarea>
					                    </div>
					                    <button type="button" class="btn btn-primary submit-btn" onclick="addcomment()" >Add Review</button>
				                    </form>
				           </div>
				</div>
				
				
				
    </div>
</div>
<%} %>

</div>
		

<%@ include file="../../footer.jsp" %>
</body>
</html>