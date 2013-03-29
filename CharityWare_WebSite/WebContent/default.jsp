<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ page import="java.util.Enumeration"%>
<%
	if(request.getParameter("logout") != null)
	{
		if(request.getParameter("logout").equals("logMeOut"))
		{
			System.out.println("RAWR!!");
			for(Enumeration<String> i = session.getAttributeNames(); i.hasMoreElements();)
			{
				String attr = i.nextElement();
				session.removeAttribute(attr);
			}
			
			session.invalidate();
		}	
	}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset = "utf-8" >
		<title>CharityWare</title>		
		<link rel="stylesheet" href="css/homepage.css" type="text/css" media="all">
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
	</head>
	
	<body>
		<div class="body">
			<div class="main">
				<jsp:include page="header.jsp"></jsp:include>
			
				<article id="content">
					<div class="wrapper">
						<div class="contentBox">
							<div class="line1">
								<div class="line2 wrapper">
								
								<section class="col1">
									<h2><strong>R</strong>ed<span>Cross</span></h2>
									<div class="pad_bot1">
										<figure><img id="image1" src="images/img1.jpg" alt=""/></figure>
									</div>
									CharityWare has helped our charity's efficiency by reaching out to people more quickly.  
								</section>
								
								<section class="col1 pad_left1">
									<h2 class="color2"><strong>G</strong>row<span>Peace</span></h2>
									<div class="pad_bot1">
										<figure><img id="image2" src="images/img2.jpg" alt=""/></figure>
									</div>
									Our workers are up to date with information and we have faster access to data exchange. Thanks to CharityWare! 
								</section>
								
								<section class="col1 pad_left1">
									<h2 class="color3"><strong>H</strong>ope<span> </span></h2>
									<div class="pad_bot1">
										<figure><img id="image3" src="images/img3.jpg" alt=""/></figure>
									</div>
									We could reach out to children who were in need. We are surely benefiting from this. 
								</section>
								
								</div>
							</div>
						</div>
					</div>
				</article>
			
			<jsp:include page="footer.jsp"></jsp:include>  
			</div>
		</div>
	</body>
</html>