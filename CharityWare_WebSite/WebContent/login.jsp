<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="staticResources.websiteLogin"%>
<%@ page import="system.clients.CharityClient"%>
<%@ page import="system.model.Charity"%>
<%@ page import="system.model.User"%>
<%
	if(request.getParameter("txtUsername") != null && request.getParameter("txtPassword") != null)
	{
		try{
			User usr = websiteLogin.login(request.getParameter("txtUsername"), request.getParameter("txtPassword"));
			System.out.println("hey");
			if(usr != null){
				session.setAttribute("userName", usr.getUserName());
				session.setAttribute("user_Id", usr.getUser_id());
				session.setAttribute("userTypeId", usr.getUserType().getUserTypeId());
				
				switch(usr.getUserType().getUserTypeId())
				{
				case 1:
					response.sendRedirect("uclAdmin.jsp");
					break;
				case 2:
					CharityClient charityClient = new CharityClient();
					Charity ch = charityClient.getUserCharity(usr.getUser_id());
					session.setAttribute("charity_id", ch.getCharity_id());
					session.setAttribute("charity_Con", ch.getConnection_string());
					response.sendRedirect("charityAdmin.jsp");
					break;			
				}
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
		jQuery(document).ready(function(){
			/*$('#frmLogin').submit(function(){
				$.post("login.jsp",{"ajax":"true","txtUsername":$('#txtUsername').val(), "txtPassword":$('#txtPassword').val()},function(){
					//callback					
				});
				return false;
			});*/	
			

			
		});
		</script>
		<script type="text/javascript" src="js/validationLogin.js"></script>
		
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
		<link rel="stylesheet" href="css/register.css" type="text/css" media="all">
		
		<title>Login to CharityWare</title>		
</head>
<body>
	<div class="body">
		<div class="main">
	  
	    <jsp:include page="header.jsp"></jsp:include>	    
	    	<script type="text/javascript" src="js/validationLogin.js"></script>
	    
	          
	    <!-- Main Content -->  
	    <article id="content">
	      <div class="wrapper">
	        <div class="contentBox">
	          
	        	<form name="frmLogin" id="frmLogin" method="post" action="login.jsp">
			      <input type="hidden" name="LogginAttempt" id="LogginAttempt" value="LogginAttempt"/>
			      	<table style="border-spacing:5px;border-collapse: inherit;">
						<tr>
							<td> 
								
								<h2> Login In </h2> 
								
			      			</td>
						</tr>
						
						<tr>
							<td>
								Username
			        		</td>
			        		<td>
			        			<!-- <input type="text" name="txtUsername" id="txtUsername" class="registerTextbox">-->
			        			<input type="text" value="<% if(request.getParameter("txtUsername")!=null){out.print(request.getParameter("txtUsername"));} %>" name="txtUsername" id="txtUsername" class="registerTextbox" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Enter your Username." required>
							</td>
						</tr>
						
						<tr>
							<td>
								Password
			        		</td>
			        		<td>
			        		  <!-- <input type="password" name="txtPassword" id="txtPassword" class="registerTextbox"/> -->
			        		  <input type="password" value="" name="txtPassword" id="txtPassword" class="registerTextbox" placeholder="Enter Your Password." required/>
							</td>
						</tr>
						
						<tr>
							<td>
								<input class="contactSubmit" name="btnLogin" type="submit" id="btnLogin" value="LOG IN">
							</td>
						</tr>
											
						<tr>
							<td>
							<br/>
							</td>
						</tr>	
						
						<tr>
							<td>
			      				<p> Need an Account ? <a href="register.jsp">Register Here</a></p>
			      			</td>
						</tr>
					</table>
			      
      			</form>
	          
	        </div>
	      </div>
		</article>
	    <!-- Main content -->
	    
	    
	   <jsp:include page="footer.jsp"></jsp:include>   
	    
	  </div>
	</div>
</body>
</html>
