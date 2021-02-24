	$(document).ready(function() {
		
		$('#idNull').hide();
		$('#pwNull').hide(); 
		
		// ##로그인 에러
		$("#loginButton").click(function(e) {
		e.preventDefault(); //submit 버튼의 기본 기능(submit) 을 막음!
			$('#idNull').hide(); 
			$('#pwNull').hide();
			
		var inputId = $('#inputId').val();
		var inputPw = $('#inputPw').val();
			
		if (inputId == '') {
			$('#idNull').show();
		}
		
		if (inputPw == '') {
			$('#pwNull').show();
		}
		
		if (inputId == '' || inputPw == '') {
			return;
		} else {
			$('#idNull').hide();
			$('#pwNull').hide();
		}
			
			$.ajax("/mountain/member/login", {
				type: "post",
				data: {inputId:inputId, inputPw:inputPw}
			
			}).fail(function() {
				console.log("등록 실패");
					alert("아이디 또는 비밀번호를 확인해주세요.");
			//		window.location.href="/mountain/login.jsp";		
				
			}).done(function(data, status, xhr) {
				console.log("등록 성공");
				window.location.href="/mountain/";				
		
			});
		});
    });