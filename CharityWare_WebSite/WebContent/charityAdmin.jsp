<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="staticResources.websiteLogin"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="charity.model.FieldType"%>
<%@ page import="charity.model.Form"%>
<%@ page import="charity.clients.UserClient" %>
<%@ page import="charity.clients.EventClient" %>
<%@ page import="charity.clients.FormFieldsClient" %>
<%@ page import="charity.clients.FormClient" %>
<%@ page import="charity.clients.FieldTypeClient" %>
<%@ page import="charity.clients.FilledFormClient" %>
<%
if(session.getAttribute("userTypeId") == null)
{
	response.sendRedirect("login.jsp");
	return;
	//out.println("go away null"+"<br/>");
}else
{
	if(websiteLogin.isAuthenticated(session.getAttribute("userTypeId").toString(), request.getRequestURL().toString()))
	{
		//out.println("you may stay"+"<br/>");
	}
	else
	{
		response.sendRedirect("login.jsp");
		return;
	}
}
%>
<%-- 	
    <%@ page import= "java.util.TreeMap"%>
	<%@page import="XMLParse.xmlParser"%>   --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
<title>CharityWare Administration Panel</title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript" src="js/charityManager.js"></script>
		<script type="text/javascript" src="js/panelSwitcher.js"></script>
		<script type="text/javascript" src="js/validationAddUser.js"></script>
		<link rel="stylesheet" href="css/register.css" type="text/css" media="all">   	
		
		<link rel="stylesheet" href="css/FormTabStyle.css" type="text/css" media="all">	
		<%	UserClient userClient = new UserClient();
			FormClient formClient = new FormClient();
			EventClient eventClient = new EventClient();
			FormFieldsClient formFieldsClient= new FormFieldsClient();
			FieldTypeClient fieldTypeClient = new FieldTypeClient();%>
    	 <script type="text/javascript">
    	  //Number of Records inputted per User	 
	      
    	  // Load the Visualization API and the piechart package.
	      google.load('visualization', '1.0', {'packages':['corechart']});
	
	      // Set a callback to run when the Google Visualization API is loaded.
	      google.setOnLoadCallback(drawChart);
	      function drawChart(point) {
	    	  var data = new google.visualization.DataTable();
	    	  
	      }
	          
	      jQuery(document).ready(function($){
	    	  
		      $('#chart0').click(function(){
		    	  $('.content_5_charts').hide();

		    	  var data = new google.visualization.DataTable();
		    	  data.addColumn('string', 'User');
			       data.addColumn('number', 'Records');
			       <%FilledFormClient filledFormClient = new FilledFormClient();%>
			       data.addRows(<%=filledFormClient.getRecordsData(session.getAttribute("charity_Con").toString()) %>); 
			       var options = {'title':'Records inputted by each User',
			                       'width':500,
			                       'height':400};
			       
			        // Instantiate and draw our chart, passing in some options.
			        var chart0 = new google.visualization.PieChart(document.getElementById('chart0_div'));
			        chart0.draw(data, options);
			        $('#chart0_div').fadeIn();
			        return false;
			        
		      });
		    
		      $('#chart1').click(function(){
		    	  $('.content_5_charts').hide();
		    	  //Pretty Ugly way of doing things, move to ajax data rather than page load.
		    	  var data = new google.visualization.DataTable();

		    	  data.addColumn('string', 'User Account');
					data.addColumn('number', 'Records');
					
					data.addRows(<%=userClient.getCharityActiveUsers(session.getAttribute("charity_Con").toString()) %>); 
					var options = { 'title':'Active Users',
		                       		'width':500,
		                        	'height':400};
		
		        // Instantiate and draw our chart, passing in some options.
		        var chart1 = new google.visualization.PieChart(document.getElementById('chart1_div'));
		        chart1.draw(data, options);
		        $('#chart1_div').fadeIn();
			        return false;  
		      });
	      	
		      $('#chart2').click(function(){
		    	  $('.content_5_charts').hide();
		    	  //Pretty Ugly way of doing things, move to ajax data rather than page load.
		    	  var data = new google.visualization.DataTable();

		    	  data.addColumn('string', 'User Account');
				  data.addColumn('number', 'Records');
				  
				  data.addRows(<%=userClient.getCharityUsersRegistrationActivity(session.getAttribute("charity_Con").toString()) %>); 
				  var options = { 'title':'Active Users',
		                       		'width':500,
		                        	'height':400};
		
		        // Instantiate and draw our chart, passing in some options.
		        var chart2 = new google.visualization.PieChart(document.getElementById('chart2_div'));
		        chart2.draw(data, options);
		        $('#chart2_div').fadeIn();
			        return false;  
		      });
		      
	      
	      });
	      
	
		  //Tabs Scripts
	      function onBodyLoad()
	      {
	    	  init();
		  }
	      
    	</script> 
</head>
<body>
	<div class="body">
		<div class="main">
	  
	   <jsp:include page="headerLoggedIn.jsp"></jsp:include>
	   <script type="text/javascript">
      var url = '<%=websiteLogin.getSiteUrl()%>';
      var urlHibernate = '<%=session.getAttribute("charity_Con").toString()%>';
      var forms = new Array();
      
   $(document).ready(function(){
    
    $.get(url+'RESTCharity/userTypeService/charityConfig/'+urlHibernate+'/userTypes', function(data) {
      $.each(data, function(i,d){     
       $('#ddUserType').append("<option value ='" + d.userTypeId  + "'>" + d.userType + "</option>");
       $('#ddUserType2').append("<option value ='" + d.userTypeId  + "'>" + d.userType + "</option>");
       });
     
      });
    
    $.get(url+'RESTCharity/formService/forms/'+urlHibernate, function(data) {
        $.each(data, function(i,d){
         forms[i] = d.formId;
         $('#cbPermissions').append('<input type="checkbox" id="cb'+forms[i]+'"  value="'+forms[i]+'" disabled="disabled" /> <label for="cb'+forms[i]+'">'+ d.formName+'</label> <br/>');
   	   
        }); 
       }); 
      
       
    $("#ddUserType2").change(function(){
        
    	 //$('#cbPermissions').empty();
    	 for(var i=0; i<forms.length;i++)
    	 {
    	 document.getElementById('cb'+forms[i]).checked=false; 
    	 document.getElementById('cb'+forms[i]).disabled=false; 
    	 }                
    	
        var userTypeId = $(this).val();
        var permissions = new Array();
    	var e = document.getElementById("ddUserType2");
  	  var optionval = e.options[e.selectedIndex].value;
  	  if (optionval=="Select")
  	  {
  		  alert("Please select a valid Option");
  		for(var i=0; i<forms.length;i++)
   	 	{
   	 		document.getElementById('cb'+forms[i]).disabled=true; 
   	 	}   
  	  }
  	  else
  	 {
        //Show Loading Animation
        $('#userTypeLoader2').show();
        
        var userTypeId = $(this).val();
        var permissions = new Array();

        $.get(url+'RESTCharity/formPermissionsService/'+urlHibernate+'/formPermissions/'+userTypeId, function(data){
        $.each(data, function(i,d){
          permissions[i] = d;
            });
         
         //$('#cbPermissions').html('');
         
         for(var i=0; i<forms.length;i++)
         {
              for(var j=0; j<permissions.length;j++)
             {
               
              if(forms[i] == permissions[j])
              {
            //$('#cbPermissions').append('<input type="checkbox" id="cb'+forms[i]+'" value="'+forms[i]+'" checked="checked" disabled="disabled"/> <label for="cb'+forms[i]+'">'+ forms[i]+'</label> <br/>');
                var element = document.getElementById('cb'+forms[i]); 
                if(element.tagName === "INPUT" && element.type === "checkbox")
                {
                 	element.checked = "checked";
                 	element.disabled = "disabled";
                }              
              }       
             }          
           }
           //Hide Loading Animation
         $('#userTypeLoader2').hide();
            });
  	 }
       });
       }); 
       
      function deactivateUser(userID){
    
       $.post(url+'RESTCharity/userService/deactivateUserAccount/'+urlHibernate+'/'+userID,function(){
    });
    
    $('#row'+ userID).remove();
    
    return false;
      };
      
      function addUser()
      {
    	  var e = document.getElementById("ddUserType");
    	  var optionval = e.options[e.selectedIndex].value;
    	  if (optionval=="Select")
    	  {
    		  alert("Please select a valid Option");
    	  }
    	  
    	  else
    	 {
		 
          $.post(url+'RESTCharity/userService/charityConfig/'+urlHibernate+'/'+$('#txtUsername').val()+'/'+$('#txtPassword').val()+'/'+$('#txtEmail').val()+'/'+$('#ddUserType').val(),
        		  function(){
      		}); 
          alert(" User added successfully");
         }
      };
      
         function addUType()
         {
        	 if($('#txtAddUserType').val()=="")
        		 {
        		 	alert("Please enter a valid Type");
        		 }
        	 else
        		 {
              $.post(url+'RESTCharity/userTypeService/charityConfig/'+urlHibernate+'/'+$('#txtAddUserType').val(),
            		  function(){
            	   
          		});
              alert('Type Added');	
         	};
         };
         
         function addPermissions(){
		    	for(var i=0; i<forms.length;i++){
		    		var element = document.getElementById('cb'+forms[i]);	
		 			if(element.checked && !element.disabled)
		            {
		 				 var wsurl = url+'RESTCharity/formPermissionsService/formPermissions/addPermissions/'+urlHibernate+'/'+$('#ddUserType2').val()+'/'+forms[i];
				    	$.post(wsurl,function(){
				    	});
				    	alert("User Permission Added Successfully");

		            }
		    	}
		    
		    }		    
		   
         

         function search(){
     			
     		$.get(url+'RESTCharity/filledFormService/'+urlHibernate+'/filledforms/getSearchResults/'+$('#category').val()+'/'+$('#txtSearch').val(), 
     				function(data){
				var tableHTML = "<table>";
     			var cnt = 0;
     			$.each(data, function(k,v){
     				
     				if (cnt == 0)
     					{
     					//Table Headers
     					tableHTML += addAllColumnHeaders(v);
     					
     					cnt++;
     					}
     				
     				//Table Content
					tableHTML += addAllContent(v);
     			});
     			
     			//Close Table
				tableHTML += "</table>";
				
				$("#searchResult").html(tableHTML);
     		});
         }
     				
    		
		function addAllContent(myList){
    		
    		var contentHTML="<tr>";

    	    for (var i = 1 ; i < myList.length ; i=i+2) {
    	    	
    	    	contentHTML += "<td>"
    	    		contentHTML += myList[i];
    	    	contentHTML += "</td>"
    	    	
    	    }
    	    contentHTML += "</tr>";

    	    return contentHTML;
    	}
    		
    	function addAllColumnHeaders(myList){
    		
    		var headerHTML="<tr>";

    	    for (var i = 0 ; i < myList.length ; i=i+2) {
    	    	
    	    	headerHTML += "<th>"
    	    	headerHTML += myList[i];
    	    	headerHTML += "</th>"
    	    	
    	    }
    	    headerHTML += "</tr>";

    	    return headerHTML;
    	}
    	
    	
    </script>

	    <!-- Main Content -->
	    
	    <article id="content">
	      <div class="wrapper">
	        <div class="contentBox">
	        
	        	<p> 
					<h2> Charity Administration Panel </h2> 
				</p>
								
				<div id="tabs">
			        <ul> 
			            <li><a href="javascript:tabSwitch(1,5,'tab_', 'content_');" id="tab_1" class="active">Manage Application</a></li>  
			            <li><a href="javascript:tabSwitch(2,5,'tab_', 'content_');" id="tab_2">Manage Users</a></li>  
			            <li><a href="javascript:tabSwitch(3,5,'tab_', 'content_');" id="tab_3">Manage Events</a></li>
			            <li><a href="javascript:tabSwitch(4,5,'tab_', 'content_');" id="tab_4">Search</a></li>
			            <li><a href="javascript:tabSwitch(5,5,'tab_', 'content_');" id="tab_5">Statistics</a></li>  
			        </ul>
			    </div> 
			    <div class="tabbed_area">       
			       <div id="content_1" class="tabContent">
      				<!-- /*-----------------------------TEMP START---------------------*/ -->
      				<!-- Main form area including drop down selection of forms and buttons. -->
      				<fieldset id="myforms">
      					<legend>My forms</legend>
      					
      				<%
      				List<Form> userForms = null;
      				List<FieldType> allTypes = null;
      				try{	      						
	      						userForms = formClient.getForms(session.getAttribute("charity_Con").toString());
	      								
	      						allTypes = fieldTypeClient.getFieldTypes(session.getAttribute("charity_Con").toString());//fieMng.retrieve();
	      						out.println(session.getAttribute("charity_Con").toString());
	      						
	      						session.setAttribute("selectedForm", "");
	      				}catch(Exception e){e.printStackTrace();}
	      				
      					if(userForms!= null && userForms.size() > 0)
      					{
      						%>
      						    <label for="myformslist">Form name:</label>
      							<select id="myformslist" onchange="onCurrentFormChanged()">
      						<% 
      						for(int i = 0; i < userForms.size(); i++)
      						{
      							Form theForm = userForms.get(i);
      							%>
	      							<option value="<%=theForm.getFormId()%>"><%=theForm.getFormName()%>
	          				    	</option>
          				    	<%	
      						}
	      					%>
	      						</select>
	          				 	<button type="button" onclick="writeFormStructure(url,$('#myformslist').val())">View structure</button>
	          				 	<button type="button" onclick="viewCurrentFormData(url,$('#myformslist').val())">View data</button>
	          				 	<button type="button" onclick="deleteCurrentForm(url,$('#myformslist').val())">Remove this form</button>
      						<%
      						
      					}else
      					{
      						%>Sorry, it appears you have no forms defined!<%      						
      					}
      				
	      				%> 
	      			<br/>
      				<button type="button" onclick="showFormWizard()">Add new Form</button>
      				</fieldset>
      				
      				<% 
      					if(userForms!= null && userForms.size() > 0)
      					{
      						%>
      						
      						<fieldset id="currentformstructure" class="nodisplay">
	          					<div id="currentformstructurefill">
	          					
	          					</div>
	          					<button type="button" onclick="hideCurrentFormStructure()">Hide</button>
          					</fieldset>
          					
          					
          					<fieldset id="currentformdata" class="nodisplay">
	          					<div id="currentformdatafill">
	          					
	          					</div>
	          					<button type="button" onclick="hideCurrentFormData()">Hide</button>
          					</fieldset>
          					<%
      					}
      				%>
      					<fieldset id="formwizard" class="nodisplay">
    					<legend>Form Wizard</legend>
    					<label>Form name:</label>
    					<input id="formname" type="text" />
    									
    					<fieldset id = "fieldselect">
    					<legend>Field wizard</legend>
    					<label for="fieldname">Field name</label>
    					<input id="fieldname" type="text" validate="notEmpty:true, English:ture"/>
    					<label for="typeoptions">Input type</label>
      					
      					<select id="typeoptions" onchange="onRowTypeChanged()">
      					<% 
	      					for(int i = 0; i < allTypes.size(); i++)
	  						{
	      						FieldType iType = allTypes.get(i);
		      					%>
		      					<option value="<%=iType.getFieldType_id()%>"><%=iType.getField_Description()%></option>
		      					<% 
	  						}
      					%>
      					</select>
						
      					<input type="checkbox" id="rowrequired" name="rowrequired"/>
      					<label for="rowrequired">Mandatory?</label>
      					<button onclick="addRow()" type="button" >Add row</button>
      					<div id="extra" class="nodisplay"></div>
      					<div id="errmsg" class="nodisplay"></div>
      					</fieldset>
      					
      					<form id="rowset" action="FormServlet" method="post">
      					<fieldset>
      					<input type="hidden" id="argc" name="argc" value="0"/>
      					<input type="hidden" name="req" value="create"/>
      					<legend>Current rows:</legend>
      					<div id="rowsetrows"></div>
      					</fieldset>
      					
      					<button type="button" onclick="hideFormWizard()">Hide</button>
      					<button type="button" id="btnSubmitForm" onclick="createForm(url)">Create this form!</button>
      					<button type="button" id="clearbtn" onclick='removeChildren(document.getElementById("rowsetrows") ); document.getElementById("argc").value=0;'>Clear all rows</button>
      					
      					</form>
      					
      				<!--  /*--------------------------------------------------------------*/ -->
      			</div>
			     
			     <div id="content_2" class="tabContent">
			     		<ul id="menubar2">
				     		<li><a href ="#" onclick="changePanel('subContent2','viewUser'); return false;"> View Users Accounts</a> <b>|</b> </li>
	             	       	<li><a href ="#" onclick="changePanel('subContent2','addUser'); return false;"> Add Users </a> <b>|</b></li>
	             	       	<li><a href ="#" onclick="changePanel('subContent2','userPermissions'); return false;"> Users Permissions </a></li>
                        </ul>
                        
                    	<div id="viewUser" class="subContent2" style="display:none;">
	                      	<form id="viewUser" name="viewUser" method="post" action="">
								<table class="resultSet">
								
								<tr>
									<th>Username</th>
									<th>User Category</th>
									<th>Email</th>
									<th>Permissions</th>
									<th>Delete</th>
					      		</tr>
					      		<%
					      		
					      		String urlHibernate = session.getAttribute("charity_Con").toString();
		            			Map<Integer,List<String>> datamap = userClient.getForms(urlHibernate);//(TreeMap<Integer,ArrayList<String>>)DatabaseManager.readUsers();
		        				Set<Entry<Integer,List<String>>> entryset = datamap.entrySet();
		        				Iterator<Entry<Integer, List<String>>> iter =  entryset.iterator();
		            
								while (iter.hasNext()){
									 Map.Entry<Integer, List<String>> pairs = (Map.Entry<Integer, List<String>>)iter.next();
									 List<String> userDetails =  pairs.getValue();
									 Integer userId = pairs.getKey();
									 String rowId = "row" + userId;
											 
								%>
					      		
					      			<tr id=<%=rowId%>>
						      			<td><%out.println(userDetails.get(0));%></td>
						      			<td><%out.println(userDetails.get(1));%></td>
						      			<td><%out.println(userDetails.get(2));%></td>
						      			<td><%out.println(userDetails.get(3));%></td>
						      			<td style="padding: 5px; text-align: left; vertical-align: top;">
						      			<span class="tooltip" data-tooltip="Deactivate User Account">
						      				<input id="btnDeactivate" type=image name=Action value='<%=userId%>' src="images/delete.png" alt="Deactivate" onclick="deactivateUser('<%=userId%>')"/>
						      			</span>
					      				</td>
					      			</tr>
					      		<%} %>
					      		
							  </table>
					   		</form>
                    	</div>
                        
 						<div id="addUser" class="subContent2" style="display:none;">
	                      <!--   <form id="addUser" name="addUser" method="post" action=""> -->
		 						<table style="border-spacing:5px;border-collapse: inherit;">
								<tr>
									<td>Username</td>
					      			<td>								
					      				<input type="text" class="userTextbox" name="txtUsername" id="txtUsername" maxlength="20" tabindex="1" pattern="^[a-zA-Z0-9]+" placeholder="Username" required/>  
														      			</td>
					      		</tr>
					      		<tr>
									<td>Password</td>
					      			<td> 
					      				<input type="password"  class="userTextbox" name="txtPassword" id="txtPassword" maxlength="25" tabindex="2" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" placeholder="One Uppercase, Lowercase and Number." required/>
					      			</td>
					      		</tr>
					      		<tr>
									<td>Email</td>
					      			<td> 
					      				<input type="email" class="userTextbox" name="txtEmail" id="txtEmail" maxlength="250" tabindex="3" placeholder="User Email Address" required/>
					      			</td>
					      		</tr>
					      		<tr>
									  <td>User Category</td>
									<td>
										<div class="ddUserType">	
											<select id="ddUserType" name="ddUserType">
												<option>Select</option>
											</select>
										</div>
									</td> 
																			      			
					      		</tr>
								<tr>
									<td></td>
									<td> 
										<input class="contactSubmit" name="btnAddUser" type="submit" id="btnAddUser" value="ADD USER" onclick="addUser();"/>
									</td>
								</tr>
							</table>
					  <!--   </form> -->
                        </div>
				 
				 		<div id="userPermissions" class="subContent2" style="display:none;">
				 		 <!-- <form id="userPermissions" name="userPermissions" method="post" action=""> -->
		 						<table style="border-spacing:5px;border-collapse: inherit;">
		 						<tr>
									<td>User Category</td>
									<td>
										<div class="ddUserType">		
											<select id="ddUserType2" name="ddUserType2">
											<option>Select</option>
											</select>
										</div>
										<!-- <div id="userTypeLoader2" style="width:5px; height:5px; display:none;">
											<img id="userTypeLoader" src="images/ajax-loader.gif"/>
										</div> -->
										<div id="addUserType" style="display:none;">
										
										<input id="txtAddUserType" type="text" class="userTextbox" maxlength="50" tabindex="5" placeholder="User Type" required></input>
										<input class="contactSubmit" name="btnAddUserType" type="submit" id="btnAddPermission" onclick="addUType()"value="ADD USER TYPE"/>
										
										</div>
									</td>
									<td>
										<input id="btnAddUCat" type=image name="btnAddUCat" src="images/add.png" alt="Add User Category" onclick="$('#addUserType').toggle(); return false;"/>
									</td>										      			
					      		</tr>
		 						<tr>
									<td> Form Permissions</td>
					      			<td>	
						      			<div id="cbPermissions">
						      			</div>
					      			</td>
					      		</tr>
					      		<tr>
									<td></td>
									<td> 
										<input class="contactSubmit" name="btnAddPermission" type="submit" id="btnAddPermission"  onclick="addPermissions();" value="ADD PERMISSION"/>
									</td>
								</tr>
		 						
		 						</table>
		 				<!-- </form>	 -->	
		 						
				 		</div>
				 </div>  

			     
			     <div id="content_3" class="tabContent">
		     		  <div class="wrapper">
				       
								<%
									String urlHibernate2 = session.getAttribute("charity_Con").toString();
									Map<Integer,List<String>> datamap3 = eventClient.getEvents(urlHibernate2);
						        	Set<Entry<Integer,List<String>>> entryset3 = datamap3.entrySet();
						        	Iterator<Entry<Integer,List<String>>> iter3 =  entryset3.iterator();
						            
									while (iter3.hasNext()){
										List<String> eventsDetails =  iter3.next().getValue();
						            %>
						            <div class = "events">
						                <h4><span><%=eventsDetails.get(0)%></span></h4>
											<p class="pad_bot2"><strong> <%=eventsDetails.get(1)%></strong></p>
											<p class="pad_bot2"><strong>VENUE </strong><%=eventsDetails.get(2)%></p>
											<p class="pad_bot2"><strong>TIME </strong><%=eventsDetails.get(3)%></p>
						            </div>
						              <%
						              } %>
							
				      </div>		
			     </div>
			      
				<div id="content_4" class="tabContent">
					<form id="frmSearch" name="frmSearch" method="post" action="">
						<table>
							<tr>
							<td>
							<div class="ddUserType">
							<select name="category" id="category" name="category">

								<%
									String urlHibernate3 = session.getAttribute("charity_Con").toString();
									Map<Integer,String> datamap4 = formFieldsClient.getListFormFields(urlHibernate3);
						        	Set<Entry<Integer,String>> entryset4 = datamap4.entrySet();
						        	Iterator<Entry<Integer,String>> iter4 =  entryset4.iterator();
						            
									while (iter4.hasNext()){
										
										 Map.Entry<Integer, String> fields = (Map.Entry<Integer, String>)iter4.next();
										 String field =  fields.getValue();
										 Integer fieldId = fields.getKey();
										 String optionValue = fields.getValue();
											
								%>
								<option value=<%= optionValue%>>
									<%=field%>
								</option>
								<%
									}
								%>
							</select>
							</div>
							</td>
							<td>
								<input type="text" id="txtSearch" name="txtSearch" class="userTextbox" maxlength="50" tabindex="9" placeholder="Search Criteria" required />
							</td>
							<td>
								<input class="contactSubmit" name="btnSearch" type="submit" id="btnSearch" onclick="search();return false;" value="SEARCH"/>
							</td>
							</tr>
						</table>
						<br/>
						<div id="searchResult" class="resultSet">
						</div>	       
					</form>
				</div>
			     
			     <div id="content_5" class="tabContent">
			     		<ul id="menubar2">
			     			<li><a id="chart0" href ="#"> Records inputted per User </a> <b>|</b> </li>
	             	       	<li><a id="chart1" href ="#"> Charity Active Users VS Inactive Account</a> <b>|</b> </li>
	             	       	<li><a id="chart2" href ="#"> Charity Users Registration Activity </a> <b></b> </li>
	             	    </ul>
                        
                        <br/>
                        <br/>
			
			     		<!--Div that will hold thchart-->
    					<div id="chart0_div" class="content_5_charts"></div>
    					<div id="chart1_div" class="content_5_charts"></div>
    					<div id="chart2_div" class="content_5_charts"></div>
    				</div>  
			    </div>  
				
	        </div>
	      </div>
		</article>
	    <!-- Main content -->
	    
		 <jsp:include page="footer.jsp"></jsp:include>   
	      
	    
	    
	  </div>
	</div>
	</body>
</html>