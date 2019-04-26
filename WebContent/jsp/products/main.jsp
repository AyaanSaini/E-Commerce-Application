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
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/products/display-products.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/products/filter-products.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/css/products/main.css">
 <link rel="stylesheet" href="/Git_Punjabi_Fashion/index.css">
 <script type="text/javascript" src = "/Git_Punjabi_Fashion/javascripts/filter.js"></script>
 <style type="text/css">
 	img{
 		background-image: url('/Git_Punjabi_Fashion/images/temp/noImage.png');
 		width: 250px;
 		height: 275px;
 	}
 	.filter-product-rating{
 		display:block;
 		background-color: green;
 		height: 28px;
 		padding-top:3px;
 		padding-left:5px;
 		padding-right:3px;
 		padding-bottom:3px;
 		border-radius: 4px;
 	}
 	.filter-product-stars{
 		display:block;
 		height:18px;
 		overflow: hidden;
 		
 	}
 .filter-product-star{
 		font-size:18px;
 		color:white;
 		display: inline;
 		
 	}
 	.product-reviews{
 		font-size: 18px;
 		margin-top: 0px;
 		color: white;
 		
 	}
 </style>
</head>
<body>
<%
String[] adultClothingSizes = {"XXS","XS","S","M","L","XL","XXL","XXXL"};

String[] kidsClothingSizes = {"New Born","4","6","8","10","12","14","16"};

String[] adultFootwearSizes = {"4","5","5.5","6","6.5","7","8","9","10","10.5","11","12"};
String[] kidsFootwearSizes = new String[24];
int k=16;
for(int i=0;i<24;i++){
	kidsFootwearSizes[i] = ""+(k++);
}
if(request.getAttribute("genderAttribute") == null)
	request.setAttribute("genderAttribute","");
if(request.getAttribute("categoryAttribute") == null)
	request.setAttribute("categoryAttribute","");
if(request.getAttribute("sizesAttribute") == null)
	request.setAttribute("sizesAttribute","");
%>
	<%@ include file="../../header.jsp" %>
	
		
		
		<div class="main-container">
			<div class="row">
				<div class="col-md-2">
					
						<div class='product-filter'>
						<div class="filter-container">
							<div class="clear-filter">
								<form action="${pageContext.request.contextPath}/ClearFilter" method="Get">
									<button type="submit" class="btn btn-primary">Clear Filter</button>
								</form>
							</div>
						    <div class='product-filter-type'>
						            <label class="filter-header" for='filterHeader' style="visibility:hidden">fhftj</label>
						            <ul>
						                <li>
						                	<% if(request.getAttribute("genderAttribute").equals("men")){%>
							                	<label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="men" id="gender" name="gender" onchange="genderSelect('men')" checked="checked">
							                        Men
							                    </label>
						                	<%}else{ %>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="men" id="gender" name="gender" onchange="genderSelect('men')">
							                        Men
							                    </label>
						                    <%} %>
						                </li>
						                <li>
						                    <% if(request.getAttribute("genderAttribute").equals("women")){%>
							                	<label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="women" id="gender" name="gender" onchange="genderSelect('women')" checked="checked">
							                        Women
							                    </label>
						                	<%}else{ %>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="women" id="gender" name="gender" onchange="genderSelect('women')">
							                        Women
							                    </label>
						                    <%} %>
						                </li>
						                <li>
						                    <% if(request.getAttribute("genderAttribute").equals("kids")){%>
							                	<label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="kids" id="gender" name="gender" onchange="genderSelect('kids')" checked="checked">
							                        Kids
							                    </label>
						                	<%}else{ %>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="kids" id="gender" name="gender" onchange="genderSelect('kids')">
							                        Kids
							                    </label>
						                    <%} %>
						                </li>
						                
						            </ul>
						        </div>
						    
						<hr class='filter-hr'>
						<hr class='filter-hr'>
						        <div class='product-filter-type'>
						            <label class="filter-header" for='filterHeader'>Category</label>
						            <ul>
						                <li>
						                	<% if(request.getAttribute("categoryAttribute").equals("clothing")){%>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="clothing" id="category" name="category" onchange="categorySelect('clothing')" checked="checked">
							                        Clothing
							                    </label>
						                    <%}else{ %>
							                     <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="clothing" id="category" name="category" onchange="categorySelect('clothing')">
							                        Clothing
							                    </label>
						                    <%} %>
						                </li>
						                <li>
						                    <% if(request.getAttribute("categoryAttribute").equals("footwear")){%>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="footwear" id="category" name="category" onchange="categorySelect('footwear')" checked="checked">
							                        Footwear
							                    </label>
						                    <%}else{ %>
							                     <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="footwear" id="category" name="category" onchange="categorySelect('footwear')">
							                        Footwear
							                    </label>
						                    <%} %>
						                </li>
						                <li>
						                    <% if(request.getAttribute("categoryAttribute").equals("jewellary")){%>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="jewellary" id="category" name="category" onchange="categorySelect('jewellary')" checked="checked">
							                        Jewellary
							                    </label>
						                    <%}else{ %>
							                     <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="jewellary" id="category" name="category" onchange="categorySelect('jewellary')">
							                        Jewellary
							                    </label>
						                    <%} %>
						                </li>
						                <li>
						                    <% if(request.getAttribute("categoryAttribute").equals("others")){%>
							                    <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="others" id="category" name="category" onchange="categorySelect('others')" checked="checked">
							                        Others
							                    </label>
						                    <%}else{ %>
							                     <label class="filter-label"  >
							                        <input type="radio" class='filter-check' value="others" id="category" name="category" onchange="categorySelect('others')">
							                        Others
							                    </label>
						                    <%} %>
						                </li>
						            </ul>
						        </div>
						
						<hr class='filter-hr'>
						
						        <!-- <div class='product-filter-type'>
						            <label class="filter-header" for='filterHeader'>Colour</label>
						            <div class='product-filter-type-filter'>
						            <ul>
						                <li>
						                    <label class="filter-label"  >
						                        <input type="checkbox" class='filter-check'>
						                        Red
						                    </label>
						                </li>
						                <li>
						                    <label class="filter-label"  >
						                        <input type="checkbox" class='filter-check'>
						                        Yellow
						                      </label>
						                </li>
						                <li>
						                    <label class="filter-label" >
						                        <input type="checkbox" class='filter-check'>
						                        Orange
						                      </label>
						                </li>
						                <li>
						                    <label class="filter-label" >
						                        <input type="checkbox" class='filter-check'>
						                        Grey
						                    </label>
						                </li>
						                <li>
						                    <label class="filter-label"  >
						                      <input type="checkbox" class='filter-check'>
						                      Black
						                    </label>
						                </li>
						                <li>
						                    <label class="filter-label"  >
						                      <input type="checkbox" class='filter-check'>
						                      Blue
						                    </label>
						                </li>                
						            </ul>
						          </div>
						        </div> -->
						
						        <hr class='filter-hr'>
						    
						                <div class='product-filter-type sizes-div'>
						                    <label class="filter-header" for='filterHeader'>Size</label>
						                    <div class='product-filter-type-filter'>
						                    <form name="sizesForm">
						                      <ul class="clothing-sizes-adult" id="clothing-sizes-adult">
						                      <%for(int i=0;i<adultClothingSizes.length;i++){ %>
						                          <li>
						                              <label class="filter-label"  >
						                                  <input type="checkbox" class='filter-check' id="sizes" value="<%=adultClothingSizes[i] %>" >
						                                  <%=adultClothingSizes[i] %>
						                              </label>
						                          </li> 
						                          <%} %>   
						                                               
						                      </ul>
						                      <ul class="footwear-sizes-adult" id="footwear-sizes-adult">
						                      <%for(int i=0;i<adultFootwearSizes.length;i++){ %>
						                              <li>
						                                  <label class="filter-label"  >
						                                      <input type="checkbox" class='filter-check' id="sizes" value="<%=adultFootwearSizes[i] %>">
						                                      <%=adultFootwearSizes[i] %>
						                                  </label>
						                              </li> 
						                              <%} %>                           
						                      </ul>
						                       <ul class="clothing-sizes-kids" id="clothing-sizes-kids" >
						                       <%for(int i=0;i<kidsClothingSizes.length;i++){ %>
						                          <li>
						                              <label class="filter-label"  >
						                                  <input type="checkbox" class='filter-check' id="sizes" value="<%=kidsClothingSizes[i] %>">
						                                 	<%=kidsClothingSizes[i] %>
						                              </label>
						                          </li>    
						                          <%} %>                         
						                      </ul>
						                      <ul class="footwear-sizes-kids" id="footwear-sizes-kids">
						                      <%for(int i=0;i<kidsFootwearSizes.length;i++){ %>
						                              <li>
						                                  <label class="filter-label"  >
						                                      <input type="checkbox" class='filter-check' id="sizes" value="<%=kidsFootwearSizes[i] %>">
						                                      <%=kidsFootwearSizes[i] %>
						                                  </label>
						                              </li>                            
						                              <%} %>
						                      </ul>
						                      </form>
						                  </div>
						                </div>
						        </div>
					</div>
				</div>
				<div class="col-md-10">
					<div class="filter-Products-container">
						<div class='products-display'>
						  <div class='row'>
<%
List listOfProducts = (List)request.getAttribute("filteredProducts");
if(listOfProducts.isEmpty()){
%>
	<div class="main-page-message">
		Sorry! No product Available.
	</div>
<%
}else{
Iterator itr = listOfProducts.iterator();
String pathToImages = "";


						  	 while(itr.hasNext())
						  	 { 
						  		
						  		String[] product = null;
						  		//Object product = null;
						  		 try{
						  			product = (String[])itr.next();
						  			//BufferedInputStream br = (BufferedInputStream)itr.next();
						  			
						  		 }catch(Exception e){
						  			 //BufferedInputStream br = (BufferedInputStream)itr.next();
						  			 e.printStackTrace();
						  		 }
						  			
						  		
						  		
						  	%>
						  	
						  	
						  	
						    <div class='col-md-3 product-block'>    
						      <a href="${pageContext.request.contextPath}/ProductDetails?productId=<%=product[0]%>">
						      <div class='products-view-product'>        
						          <div class='products-view-product-center'>
						            <div class='row '>
						              <div class='col-md-12 products-view-product-image' >
						              	
						                <img width='90%' height='275px' src="${pageContext.request.contextPath}/GetImage?imageId=<%=product[0] %>" />
						              
						              	
						              </div> 
						            </div>
						            <div class='row'>
						              <div class='col-md-12 products-view-product-title'>
						               <%=product[1].toLowerCase() %>
						              </div>
						            </div>
						            <div class='row'>
						              <div class='col-md-8 filter-product-price'>
						              	<%
						              		double mrp = Double.parseDouble(product[2]);
						              		double discount = Double.parseDouble(product[3]);
						              	double discountedPrice =  mrp - (mrp*discount/100);%>
						                <span class='product-discountedPrice'>$<%=discountedPrice %></span>	
						                <% if(discount > 0.0){ %>					                
						                <span class='product-mrp' ><del>$<%=product[2] %></del> </span>&nbsp;&nbsp;
						                <%} %>
						              </div>
						              <%
						              	double rate = Math.round(Double.parseDouble(product[4])*10.0)/10.0;
						              	if(rate > 0.0){
						              %>
						              	<div class="col-md-3 filter-product-rating">
						              		    <span class='glyphicon glyphicon-star filter-product-star'></span>
				                            <span class='product-reviews'>(<%=rate %>)</span>
						              	</div>
						             <%} %>
						              
						            </div>
						          </div>
						        </div>
						      </a>
						      </div>			     
						      
						      <% } }%>
						      
						  </div>
						</div>

					</div>
				</div>
			</div>
		</div>
	<%@ include file="../../footer.jsp" %>
	
	<form action="${pageContext.request.contextPath}/FilterProducts" id="filterProductForm" method = "post">
		<input type="hidden" id="titleHidden" name="titleHidden">
		<input type="hidden" id="genderHidden" name="genderHidden">
		<input type="hidden" id="categoryHidden" name="categoryHidden">
		<input type="hidden" id="sizesHidden" name="sizesHidden">
	</form>
	
</body>
</html>