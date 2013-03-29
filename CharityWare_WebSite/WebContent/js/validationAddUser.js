jQuery(document).ready(function(){
			
	$('#btnAddUser').click(function(){
				
		$('#txtUsername').attr('style', '');
		$('#txtPassword').attr('style', '');
		$('#txtChEmail').attr('style', '');
					
		if($('#txtUsername').val() == undefined || $('#txtUsername').val() == "")
		{					
			$("#txtUsername").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtPassword').val() == undefined || $('#txtPassword').val() == "")
		{					
			$("#txtPassword").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtEmail').val() == undefined || $('#txtEmail').val() == "")
		{					
			$("#txtEmail").css({"border":"1px solid #FF0000"});
		}
		
		/*alert("bang");
		$.post('url',{param : "val"}, function(){
		
		
		});*/
		
		return false;
	});
});
		