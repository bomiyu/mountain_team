$(function(){
	
	$('#titleNull').hide();
	$('#contentNull').hide();
	
	$('#submitBtn').click(function(e){
		e.preventDefault();
		
		if ($('#title').val() && $('#content').val()){
			
			if ($('#replyCheck').is(':checked')){
				$('#reply').val(0);
			}
			
			$('#registerForm').submit();		
		}
		
		if (! $('#title').val()){// null, ''
			$('#titleNull').show();
		} else {
			$('#titleNull').hide();
		}
		
		if (! $('#content').val()){// null, ''
			$('#contentNull').show();
		} else {
			$('#contentNull').hide();
		}
		
	});

});