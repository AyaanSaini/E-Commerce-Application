<nav class="navbar navbar-default navbar-fixed-top ">
  <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>                        
        </button>
        <a class="navbar-brand" href="/Git_Punjabi_Fashion/index.jsp"><span class="navbar-brand">Punjabi Fashion</span>
          <!-- <img src="" class="logo" alt='image'> -->
        </a>          
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
         <!--  <li class='dropdown'>
              <a href=""class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Men<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/products/men/sherwani">Sherwani</a></li>
                <li><a href="/products/men/kurta">Kurta Payjama</a></li>
                <li><a href="/products/men/turban">Turban</a></li>
                <li><a href="/products/men/jutti">Punjabi Jutti</a></li>
              </ul>
          </li>

          <li class='dropdown'>
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Women<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/products/women/suit">Punjabi Suit</a></li>
                <li><a href="/products/women/lahanga">Lahanga</a></li>
                <li><a href="/products/women/saree">Saree</a></li>
                <li><a href="/products/women/jutti">Punjabi Jutti</a></li>
              </ul>
          </li>

          <li class='dropdown'>
              <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Kids<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/products/kids/dress">Dress</a></li>
                <li><a href="/products/kids/footwear">Footwear</a></li>
              </ul>
          </li>

          <li class='dropdown'>
              <a href="" class="dropdown-toggle" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">More<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/products/more/jewellary">Jewellary</a></li>
                <li><a href="/products/more/gottapatti">Gotta Patti</a></li>
                <li><a href="/products/more/Bangles">Bangles</a></li>
              </ul>
          </li>  -->

          <li class='dropdown' >
                <a href="/Git_Punjabi_Fashion/SearchProductsController" style="font-size:16px;"><span class="glyphicon glyphicon-home"></span> Shop </a>
        </li> 
		 
        </ul>
        
        <ul class="nav navbar-nav navbar-right">
            
            
             <% String user, userRole;           	
            	if(session.getAttribute("session_user") == null ||session.getAttribute("session_user").equals("")||session.getAttribute("session_user_role")==null ){
            		user = "Guest";
            		userRole="Guest";
            	}else{            		
            		user = session.getAttribute("session_user").toString();
            		userRole=session.getAttribute("session_user_role").toString();
            	}
            	if(user.equals("Guest")){
            		%>
            		<li><a href="javascript:void(0)"><span class="glyphicon glyphicon-user" ></span>&nbsp;<%= user %></a></li>
            		<%
            	}
            	else if(userRole.equalsIgnoreCase("customer")){
            		%>
            		<li class="dropdown user">
            			<a href="javascript:void(0)" style="width:130px; text-align:center;" class="dropdown-toggle" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false" ><span class="glyphicon glyphicon-user" ></span>&nbsp;<%= user %></a>
            			<ul class="dropdown-menu">
			                <li><a href="/Git_Punjabi_Fashion/jsp/login/change_password.jsp">Change Password</a></li>
			            </ul>
            		</li>
            		<%
            	}
            	else if(userRole.equalsIgnoreCase("admin")){
            		%>
            		<li class="dropdown user">
            			<a href="javascript:void(0)" style="width:130px" class="dropdown-toggle" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user" ></span>&nbsp;<%= user %></a>
            			<ul class="dropdown-menu">
			                <li><a href="/Git_Punjabi_Fashion/jsp/admin/addproduct.jsp">Add Product</a></li>
			                <li><a href="/Git_Punjabi_Fashion/jsp/admin/editproduct.jsp">Edit Product Details</a></li>
			                <li><a href="/Git_Punjabi_Fashion/jsp/admin/deleteproduct.jsp">Remove Product</a></li>
			                <li><a href="/Git_Punjabi_Fashion/jsp/login/change_password.jsp">Change Password</a></li>
			              </ul>           		
            		</li>
            		<%
            	}
            %>
           	
            
            
            <%
            	if(session.getAttribute("session_user") == null ||session.getAttribute("session_user").equals("") ){
            %>
            <li><a href="/Git_Punjabi_Fashion/jsp/login/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            <%
            	}else{
            %>
            <li><a href="/Git_Punjabi_Fashion/jsp/login/logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            <%} %>
        </ul>
        
			<form class="navbar-form" action="${pageContext.request.contextPath}/FilterProducts" method="post">
		        <div class="input-group" style="display:inline; margin-left: 40px;">
		          <div class="input-group" style="width: 700px;">
		            <input type="text" name="titleLike" id="titleLike" class="form-control">
		            <span class="input-group-btn"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button></span>
		          </div>
		        </div>
		      </form>
	      
        
      </div>         
  </div>
</nav>