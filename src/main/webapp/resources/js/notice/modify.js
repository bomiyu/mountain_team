$(function(){
	
	var category = $('#p-category').val();
	var keyword = $('#p-keyword').val();
	var curPage = $('#p-curPage').val();
	var amount = $('#p-amount').val();
	
	$('#modifyBtn').click(function(e){
		e.preventDefault();
		
		var reply = 1;
		if ($('#replyCheck').is(':checked')){
			reply = 0;
		}
		
		var notice = {
			no: no,
			category: $('#category').val(),
			title: $('#title').val(),
			content: $('#content').val(),
			reply: reply
		};
		
		$.ajax(root + '/notice/modify?no=' + no, {
			method: 'post',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(notice)	// '{}'
		}).done(function(data, status, xhr){
	    	//console.log(data);// = xhr.responseText
			location.replace(root + '/notice/get?no=' + no
				+ '&category=' + category + '&keyword=' + keyword
				+ '&curPage=' + curPage + '&amount=' + amount
			);
	    }).fail(function(e){
	    	console.log(e);
	    });

	});
	
	$('#cancelyBtn').click(function(){
		location.replace(root + '/notice/get?no=' + no
				+ '&category=' + category + '&keyword=' + keyword
				+ '&curPage=' + curPage + '&amount=' + amount
		);
	});
	
});