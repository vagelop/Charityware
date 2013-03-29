jQuery(document).ready(function(){
			
	$('#btnContactSubmit').click(function(){
				
		$('#txtContactEmail').attr('style', '');
		$('#txtContactName').attr('style', '');
		$('#txtContactComment').attr('style', '');
					
		if($('#txtContactEmail').val() == undefined || $('#txtContactEmail').val() == "")
		{					
			$("#txtContactEmail").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtContactName').val() == undefined || $('#txtContactName').val() == "")
		{					
			$("#txtContactName").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtContactComment').val() == undefined || $('#txtContactComment').val() == "")
		{					
			$("#txtContactComment").css({"border":"1px solid #FF0000"});
		}
		
		
		return false;
	});
});
		