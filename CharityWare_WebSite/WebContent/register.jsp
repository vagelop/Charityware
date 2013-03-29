<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="staticResources.websiteLogin"%>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
		<link rel="stylesheet" href="css/register.css" type="text/css" media="all">
		
		<title>CharityWare - Register your Charity</title>		
	</head>
	
	<body>
		<div class="body">
			<div class="main">
			
			<jsp:include page="header.jsp"></jsp:include>  
			<script type="text/javascript" src="js/validationRegister.js"></script>
			<script type="text/javascript">
			
			 	var url = '<%=websiteLogin.getSiteUrl()%>';			   	
				function Register()
				{
					$.post(url+'RESTSystem/charityService/addCharity/'+$('#txtCharity').val()+'/'+$('#txtReg').val()+'/'+$('#txtChEmail').val()+'/'+$('#txtChDescription').val()+'/'+$('#txtAdminUsername').val()+'/'+$('#txtAdminPassword').val(),function(){
					});
					
					alert("Your Request has successfully been registered, we'll get back with you shortly");
				}
			</script>
				<!-- Main Content -->
				<article id="content">
				<div class="wrapper">
				<div class="contentBox">
				
					<form name="frmRegister" method="post" action="">
					
					<table style="border-spacing:5px;border-collapse: inherit;">
						<tr>
							<td> 
								<p> 
									<h2> Register </h2> 
								</p>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Name
							</td>
							<td>
								<input type="text" class="registerTextbox" name="txtCharity" id="txtCharity" maxlength="100" pattern="[a-zA-Z0-9]+" placeholder="Charity Name" tabindex="11" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Registration Number
							</td>
							<td>
								<input type="text" class="registerTextbox" name="txtReg" id="txtReg" maxlength="100" tabindex="12" pattern="[0-9]{5,7}" placeholder="Charity Registration No" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Email
							</td>
							<td>
								<input type="email" class="registerTextbox" name="txtChEmail" id="txtChEmail" maxlength="250" tabindex="13" placeholder="Charity Email Address" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Description
							</td>
							<td>
								<textarea cols=65 rows=4 id="txtChDescription" name="txtChDescription" class="registerTextbox" maxlength="300" tabindex="14" pattern="[a-zA-Z0-9]+" placeholer="Charity Description" required></textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Administrator Username
							</td>
							<td>
								<input type="text" class="registerTextbox" name="txtAdminUsername" id="txtAdminUsername" maxlength="20" tabindex="15" pattern="^[a-zA-Z0-9]+" placeholder="Charity Administrator Username" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Administrator Password
							</td>
							<td>
								<input type="password"  class="registerTextbox" name="txtAdminPassword" id="txtAdminPassword" maxlength="25" tabindex="16" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" placeholder="Password should contain a minimum of one Uppercase, Lower and Number." required>
							</td>
						</tr>	
						
						<tr>
							<td>
								<input class="registerSubmit" name="btnRegister" type="submit" id="btnRegister" value="REGISTER" onclick="Register()"/>
							</td>
						</tr>
						
						<tr>
							<td>
								<br/>
							</td>
						</tr>	
						
						<tr>
							<td>
								<p> <a href="registerHelp.jsp"> Require Assistance ?</a></p>
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
