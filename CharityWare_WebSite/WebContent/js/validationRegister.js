jQuery(document).ready(function(){
			
	$('#btnRegister').click(function(){
				
		$('#txtCharity').attr('style', '');
		$('#txtReg').attr('style', '');
		$('#txtChEmail').attr('style', '');
		$('#txtChDescription').attr('style', '');
		$('#txtAdminUsername').attr('style', '');
		$('#txtAdminPassword').attr('style', '');
		
					
		if($('#txtCharity').val() == undefined || $('#txtCharity').val() == "")
		{					
			$("#txtCharity").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtReg').val() == undefined || $('#txtReg').val() == "")
		{					
			$("#txtReg").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtChEmail').val() == undefined || $('#txtChEmail').val() == "")
		{					
			$("#txtChEmail").css({"border":"1px solid #FF0000"});
		}
		if($('#txtChDescription').val() == undefined || $('#txtChDescription').val() == "")
		{					
			$("#txtChDescription").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtAdminUsername').val() == undefined || $('#txtAdminUsername').val() == "")
		{					
			$("#txtAdminUsername").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtAdminPassword').val() == undefined || $('#txtAdminPassword').val() == "")
		{					
			$("#txtAdminPassword").css({"border":"1px solid #FF0000"});
		}
		
		
		
		
		
		
		/*alert("bang");
		$.post('url',{param : "val"}, function(){
		
		
		});*/
		
		return false;
	});
});
		