$(document).ready(function() {
		
		// ##패스워드 패턴 확인 멘트 - hide
		$("#pwPattern").hide();
		
		// ##닉네임 중복 확인 멘트 - hide
		$('#nicknameOk').hide();
		$('#nicknameDup').hide();
		$('#nicknameNull').hide();	
		
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
						$('#nicknameNull').show(); //inputId가 빈 스트링일때 아이디를 적어주세요 멘트 어떻게 하냐!!!
					}
				} 
			}).fail(function() {
	
			});
		});
		
		// ##(닉네임 수정 후) 중복 검사를 해주세요.
		function nnKeyUp(){
			$("#nicknameDupCheck").removeAttr("disabled"); // 버튼 비활성화 해제
			$("#checkedNn").val('');
			$("#modify").click(function(e) {
				e.preventDefault();
				if($("#checkedNn").val() == ''){
					alert("닉네임 중복 확인을 해주세요.");
					return false;
				} else {
				$("#modifyForm1").submit();
				}
			});
		}
		$("#inputNickname").keyup(nnKeyUp);
		
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
       
        
    	// ##이메일 주소 선택
        $('#select').change(function() {
            if ($('#select').val() == 'directly') {
                $('#emailSelect').attr("disabled", false);
                $('#emailSelect').val("");
                $('#emailSelect').focus();
            } else {
                $('#emailSelect').val($('#select').val());
            }
        });
        
        // ##이메일 값을 합쳐서 name="email"으로 보내기
        function setEmailInput() {
        	var email = $("#emailFront").val() + "@" + $("#emailSelect").val();  
        	$("#email-input").val(email);
        }
        
         $("#select").change(setEmailInput);
        $("#emailFront").keyup(setEmailInput);
        $("#emailSelect").keyup(setEmailInput);
        
        // ##비밀번호 값을 수정하면 pattern 멘트
        function showPwPattern() {
        	$("#pwPattern").show();
        }
        $("#staticPw").keyup(showPwPattern);
                
    });