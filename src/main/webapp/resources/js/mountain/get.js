$(function(){

	var curMname = $('#mname').val();// 변경 전 이름
	
	if (result == 'modSuccess') {
		swal({
			title: "Modified",
			text: "산이 수정되었습니다.",
			icon: "success",
			button: "close"
		});
	}
	
	/* 수정 */
	$('#submitBtn').click(function(){
		/* 산 이름 pattern 체크 */
		var mname = $('#mname').val();
		var reg = /^[가-힣]+산$/;
		
		if (!mname.match(reg)){
			swal({
				title: "Not available",
				text: "산 이름을 정확히 작성해주세요.",
				icon: "warning",
				button: "close"
			});
			return ;
		}
		
/*		var status = 0;
		const st = $('[name="status"]');
		if( st[1].checked ) {
			status = 1;
		}
*/
		
		/* 산 이름 UNIQUE 체크 */
		if(curMname != mname){// mname 변경하는 경우
			$.ajax(root + '/check', {
				type: 'POST',
				contentType: 'application/json',
				data: mname
			}).fail(function(){
				swal({
					title: "Not available",
					text: "이미 존재하는 산 이름입니다.",
					icon: "warning",
					button: "close"
				});
			});
		}
		
		var no = Number($('#no').val());
		$.ajax(root + '/modify', {
			type: 'POST',
			data: new FormData($('#modifyForm')[0]),
			enctype: 'multipart/form-data',
			contentType : false,
	        processData : false,
			cache: false
		}).done(function(){
		  location.replace(root + '/get?no=' + no + '&curPage=' + curPage + '&amount=' + amount + '&keyword=' + keyword);		  
		});
	});


	/* 삭제 */
	$('#removeBtn').click(function(){
		swal({
		  title: "Are you sure?",
		  text: "게시글을 삭제하시겠습니까?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((isConfirm) => {
		  if (isConfirm) {
			var no = $('#no').val();
		
			$.ajax(root + '/remove?no=' + no + '&curPage=' + curPage + '&amount=' + amount + '&keyword=' + keyword, 
			{
				type: 'DELETE'
			}).done(function(cri){
			  //console.log(cri);
			  //console.log(JSON.stringify(cri));
			  //console.log($.parseXML(cri));
			  //console.log($($.parseXML(cri)));
			  //console.log('curPage: ' + $($.parseXML(cri)).find('curPage'));
			  location.replace(root + '/list?curPage=' + curPage + '&amount=' + amount + '&keyword=' + keyword);
		  	});
		  }
	   });
	});	


	/* 버튼 숨기기 */
	$('#cancelBtn').hide();
	$('#submitBtn').hide();
	$('#statusForm').hide();

	$('#upload').hide();

	/* 수정 버튼 클릭 */
	$('#modifyBtn').click(function(){// 입산여부도 수정하자!
		$(this).hide();
		$('#removeBtn').hide();
		
		$('#cancelBtn').show();
		$('#submitBtn').show();
		
		$('#modifyForm input').removeAttr('readonly');
		$('#modifyForm textarea').removeAttr('readonly');
		
		$('#statusView').hide();
		$('#statusForm').show();
		
		$('#upload').show();
	});
	
	/* 취소 버튼 클릭 */
	$('#cancelBtn').click(function(){
		location.reload();// form값 되돌리기
	});

	if(!isManager) {
		$('#modifyBtn').hide();
		$('#removeBtn').hide();
	}
	
	
	
	
	
	
	/* navigation */
	$('.nav #link1').click(function(){
		$('.nav_body').hide();
		$('#nav_mountain').show();
	});
	
	$('.nav #link2').click(function(){
		$('.nav_body').hide();
		$('#nav_restaurant').show();
	});
	
	$('.nav #link3').click(function(){
		$('.nav_body').hide();
		$('#nav_festival').show();
	});
	
	$('.nav #link4').click(function(){
		$('.nav_body').hide();
		$('#nav_place').show();
	});
	
	$('.nav #link5').click(function(){
		$('.nav_body').hide();
		$('#nav_map').show();
	});
	
	
});