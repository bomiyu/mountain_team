$(function(){
	
	showModal(result);
	
	$('#removeBtn').hide();
	$('#modifyBtn').hide();

	$('#newReplyBtn').hide();
	$('#reply').attr('placeholder', '댓글을 쓰려면 로그인을 해주세요');
	$('#reply').attr('readonly', true);
	
	
	if(canReply) {
		if(logined) {
			$('#newReplyBtn').show();
			$('#reply').removeAttr('placeholder');
			$('#reply').removeAttr('readonly');
		}
		$('#replyList').show();
	} else {
		$('#reply').attr('placeholder', '해당 게시물에 댓글을 달 수 없습니다.');
		$('#replyList').hide();
	}
	
	if(logined && isManager) {/* 관리자 */
		$('#removeBtn').show();
		$('#modifyBtn').show();
	}
	
	function showModal(result){
		if(result == 'modSuccess') {
			swal({
			  title: "Modified",
			  text: "공지가 수정되었습니다.",
			  icon: "success",
			  button: "close",
			});
		}
	};
	
	$('#removeBtn').click(function(e){
		e.preventDefault();
		
		var category = $('#category').val();
		var keyword = $('#keyword').val();
		var curPage = $('#curPage').val();
		var amount = $('#amount').val();
		
		
		swal({
		  title: "Are you sure?",
		  text: "게시글을 삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((isConfirm) => {
		  if (isConfirm) {
		    //$('#removeForm').submit();
		    
		    $.ajax(root + '/notice/remove/' + no, {// 페이지 이동하지 않고 처리한 다음
		    	method: 'delete'
		    }).done(function(data, status, xhr){
		    	//console.log(data);// = xhr.responseText
				location.replace(root + '/notice/list?'
				+ 'category=' + category + '&keyword=' + keyword
				+ '&curPage=' + curPage + '&amount=' + amount
				);
		    }).fail(function(e){
		    	console.log(e);
		    });

		  }
		});
		
	});
	
/*	$('#modifyBtn').click(function(){
		location.replace(root + '/notice/modify?no='+ no);
	});
*/	

	$('h3').click(function(){
		location.href = root + '/notice/list';
	});
	
	
	
	/* Reply */
	function getReplyList(){
		$.getJSON(root + '/nreplies/list/' + no)
		.done(function(data){
			$('#replyTable').empty();// 하위 요소 제거
			
			var now = new Date();
			$.each(data, function(i, item){// (index, element)
				var diff = (now.getTime() - item.updatedate)/(1000*60*60);// 시간 단위
				
				if(diff < 1) {// 1시간 미만
					diff = '방금 전';
				} else if(diff < 24) {// 1시간 ~ 24시간
					diff = Math.floor(diff) + '시간 전';
				} else {// 24시간 이상
					diff = Math.floor(diff/24) + '일 전';
				}
			
				$('#replyTable').append(
					 '<tr>'
					+	'<td>'
					+	  '<div><input type="text" name="reply" value="' + item.reply + '" class="form-control replies" readonly>'
					+	  '<input type="hidden" name="no" value="' + item.no + '" class="reply_no"></div>'
					+	  '<div><strong>' + item.replyer + '</strong><small class="float-right">' + diff + '</small></div>'
					+	  '<div>'
					+		'<i class="fas fa-ellipsis-h"></i>'
					+		'<a class="modReplyBtn mx-2"><small>수정</small></a>'
					+		'<a class="delReplyBtn"><small>삭제</small></a>'
					+		'<a class="cancelReplyBtn ml-2"><small>취소</small></a>'
					+		'<a class="submitReplyBtn ml-2"><small>등록</small></a>'
					+	  '</div>'
					+	'</td>'
					+ '</tr>'
				
				);
				
				//$('.modReplyBtn').hide();
				//$('.delReplyBtn').hide();
	
			});
			
		});
	};
	
	
	getReplyList();
	
	
	
	$('.fa-ellipsis-h').click(function(){// 나중에 append된 코드에 적용이 안 되네 후
		$(this).next().show();
		$(this).next().next().show();
	});
	
	// Reply Register
	$('#newReplyBtn').click(function(){
		var data = {
			reply: $('#reply').val(),
			notice_no: Number($('#notice_no').val()), // string으로 들어가는데 흠
			member_no: Number($('#member_no').val())
		};
		console.log(data);
	
		$.ajax(root + '/nreplies/new', {
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data)// 문자열화 -> json 객체 내의 키로 인식되도록
		}).done(function(data){
			console.log("success: " + data);
			
			getReplyList();	
			$('#reply').val('');
				
			
		}).fail(function(err){
			console.log(err);
		});
	
	});
	
	
	// Reply Modify
	var replyVal;
	$('.modReplyBtn').click(function(){// 나중에 append된 코드에 적용이 안 되네 후
		var replies = $(this).closest('td').find('.replies');
		replyVal = replies.val();
		$('.replies').attr('readonly', true);
		//$(this).closest('td').find('.replies').removeAttr('readonly');
		replies.removeAttr('readonly');
		replies.focus();
	});
	
	
	$('.submitReplyBtn').click(function(){
		var data = {
			no: Number($(this).closest('td').find('.reply_no').val()),
			reply: $(this).closest('td').find('.replies').val()
		};
		console.log(data);
		console.log(data.no);
	
		$.ajax(root + '/nreplies/' + data.no, {
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data)
		}).done(function(data){
			console.log("success: " + data);
			
			getReplyList();	
			
		}).fail(function(err){
			console.log(err);
		});
	});
	
	
	// Reply Remove
	$('.delReplyBtn').click(function(){
		var reply_no = Number($(this).closest('td').find('.reply_no').val());
		
		$.ajax(root + '/nreplies/' + reply_no, {
			type: 'DELETE'
		}).done(function(data){
			console.log("success: " + data);
			
			getReplyList();	
			
		}).fail(function(err){
			console.log(err);
		});
	});
	
	
});