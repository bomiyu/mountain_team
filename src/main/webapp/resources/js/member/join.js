	$(document).ready(function() {
	
		// ##아이디 중복 확인 멘트 - hide
		$('#idOk').hide();
		$('#idDup').hide();
		$('#idNull').hide();
		
		// ##아이디 패턴 검사
		$('#idPattern').show();
		$('#idPattern').attr("style", "color:gray");
		
		// ##닉네임 중복 확인 멘트 - hide
		$('#nicknameOk').hide();
		$('#nicknameDup').hide();
		$('#nicknameNull').hide();	
		
		
		// ##아이디 중복 검사 버튼
		$("#idDupCheck").click(function(e) {
			e.preventDefault();
			var inputId = $('#inputId').val();
						
			$.ajax({
				type: "get",
				url: "/mountain/member/idDupCheck",
				data: {inputId:inputId}
			}).done(function(data) {
				console.log("아이디 중복 검사");
				if(data != null) {
					$('#idNullError').hide();
					if(data == '0' ) {
						console.log("사용할 수 있음");
						$('#idDup').hide();
						$('#idNull').hide();
						$('#idPattern').hide();
						alert("아이디를 사용할 수 있습니다.");
						$("#checkedId").val('y');
						$('#idOk').show();
					} else if(data == '-1') {
						console.log("중복 아이디");
						$('#idOk').hide();
						$('#idNull').hide();
						$('#idPattern').hide();
						alert("중복된 아이디입니다.");
						$('#idDup').show();
					} else if(data == '-2') {
						console.log("아이디 null");
						$('#idDup').hide();
						$('#idNull').hide();
						$('#idPattern').show();
						$('#idPattern').attr("style", "color:tomato");
						$('#idNull').show();
					} else if(data == '-3') {
						console.log("아이디 패턴 x");
						$('#idOk').hide();
						$('#idDup').hide();
						$('#idNull').hide();
						$('#idPattern').show();
						$('#idPattern').attr("style", "color:tomato"); //inputid 패턴식 false
						
					}
				} 
			}).fail(function() {
	
			});
		});
		
  		// ##아이디 중복 검사를 해주세요.
		$("#join").click(function(e) {
			e.preventDefault();
			if($("#checkedId").val() == ''){
				alert("아이디 중복 확인을 해주세요.");
		//		 $("input[name='checked_id']").eq(0).focus(); //커서 옮기기
				return false;
			} 
			$("#joinForm1").submit();
		}); 		
	
		// ##닉네임 중복 검사 버튼
		$("#nicknameDupCheck").click(function(e) {
			e.preventDefault();
			var inputNickname = $('#inputNickname').val();
			
			$.ajax({
				type: "get",
				url: "/mountain/member/nicknameDupCheck",
				data: {inputNickname:inputNickname}
			}).done(function(data) {
				console.log("닉네임 중복 검사");
				if(data != null) {
					if(data == '0' ) {
						console.log("사용할 수 있음");
						$('#nicknameDup').hide();
						$('#nicknameNull').hide();
						alert("닉네임을 사용할 수 있습니다.");
						$("#checkedNn").val('y');
						$('#nicknameOk').show();
					} else if(data == '-1') {
						console.log("중복된 닉네임");
						$('#nicknameOk').hide();
						$('#nicknameNull').hide();
						alert("중복된 닉네임입니다.");
						$('#nicknameDup').show();
					} else if(data == '-2') {
						console.log("닉네임 null");
						$('#nicknameOk').hide();
						$('#nicknameDup').hide();
						$('#nicknameNull').show();
					}
				} 
			}).fail(function() {
	
			});
		});
		
		
 		// ##닉네임 중복 검사를 해주세요.
		$("#join").click(function(e) {
			e.preventDefault();
			if($("#checkedNn").val() == ''){
				alert("닉네임 중복 확인을 해주세요.");
				return false;
			}
			$("#joinForm1").submit();
		});
 		
 		
 		// ##키업 될때, 아이디, 닉네임 중복 확인 필요
 		function setIdReset() {
 			$("#checkedId").val('');
 		}	
 	        $("#inputId").keyup(setIdReset);
 	        
 		function setNnReset() {
 			$("#checkedNn").val('');
 		}	
 	        $("#inputNickname").keyup(setNnReset);
		
 		
 		// ##비밀번호 패턴 에러
 		$('#join').click(function() {
 			if($('#pwPatternError').val() != null) {
 				//비밀번호 패턴 에러가 있다면
 				console.log("#pwPatternError");
 				$('#pwPatternError').show();
 			} else if($('#pwNullError').val() != null) {
 				//비밀번호가 null 이면	
 				$('#pwPattern').hide();
 				$('#pwPatternError').show();
 			}
 			
 			
 		}); 
 		
    	// ##이메일 주소 선택
        $('#select').change(function() {
            if ($('#select').val() == 'directly') {
                $('#textEmail').attr("disabled", false);
                $('#textEmail').val("");
                $('#textEmail').focus();
            } else {
                $('#textEmail').val($('#select').val());
            }
        });
        
        // ##이메일 값을 합쳐서 name="email"으로 보내기 
        function setEmailInput() {
        	var email = $("#email").val() + "@" + $("#textEmail").val();  
        	$("#email-input").val(email);
        }
        
        $("#select").change(setEmailInput);
        $("#email").keyup(setEmailInput);
        $("#textEmail").keyup(setEmailInput);
        
        // ##주소 값을 합쳐서 name="loc"로 보내기
        function setLocInput() {
        	var loc = $("#sample3_postcode").val() + "@" + $("#sample3_address").val() + "@" +
        	$("#sample3_detailAddress").val() + "@" + $("#sample3_extraAddress").val();
        	$("#loc-input").val(loc);
        }
  
        $("#sample3_postcode").change(setLocInput);
        $("#sample3_address").change(setLocInput);
        $("#sample3_detailAddress").keyup(setLocInput);
        $("#sample3_extraAddress").change(setLocInput);
        
    });