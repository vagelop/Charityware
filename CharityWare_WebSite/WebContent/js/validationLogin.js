jQuery(document).ready(function(){
			
	$('#btnLogin').click(function(){
				
		$('#txtUsername').attr('style', '');
		$('#txtPassword').attr('style', '');
		
			var valid = true;		
		if($('#txtUsername').val() == undefined || $('#txtUsername').val() == "")
		{					
			$("#txtUsername").css({"border":"1px solid #FF0000"});
			valid = false;
		}
		
		if($('#txtPassword').val() == undefined || $('#txtPassword').val() == "")
		{					
			$("#txtPassword").css({"border":"1px solid #FF0000"});
			valid = false;
		}
		
		
		if(!valid)
			{
			return false;
			}
		
		
	});
});