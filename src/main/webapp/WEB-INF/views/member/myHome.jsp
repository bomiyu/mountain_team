<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>


<script>
	var appRoot = '${root}';
	var conquestcnt = '${conq.conquestcnt}';
	$(document).ready(function() {
		function hideSpans() {
			$('#pwError').hide();
			$('#pwNull').hide();
		}
		hideSpans();
		$("#memberDeleteCancel1").click(function() {
			$('#pwConfirm').val('');
			$('#pwError').hide();
			$('#pwNull').hide();
		});
		$("#memberDeleteCancel2").click(function() {
			$('#pwConfirm').val('');
			$('#pwError').hide();
			$('#pwNull').hide();
		});
		$("#memberDelete").click(function() {
			$('#pwError').hide();
			$('#pwNull').hide();
			//모달창을 띄우고 비밀번호 확인이 맞으면
			//비밀번호 확인은 controller에서 함.	
			var userId = $(this).attr("data-userId"); //attribute 값
			var pwConfirm = $('#pwConfirm').val(); //value값
			//변수에 원하는 값을 넣어준다.
			$.ajax("/mountain/member/delete?" + $.param({
				userId : userId,
				pwConfirm : pwConfirm
			}), { //경로
				method : "delete" //메소드 방법(controller에서 deleteMapping으로 표시)
			//data:{id: userId, pw:pwConfirm}로 보내줬을때 읽어오질 못함
			//=> delete 방식으로 보냈을때, delete는 body가 없기 때문에 요청이 오류, 거절이 일어날 수 있다.
			//=> 해결 : 파라미터로 값을 붙여서 보내주자!
			}).fail(function() {
				console.log("삭제 실패");
				if ($('#pwConfirm').val() == '') {
					$('#pwNull').show(); //비밀번호를 입력해주세요.
				} else {
					$('#pwError').show(); //비밀번호가 일치하지 않습니다.
				}
			}).done(function() {
				console.log("삭제 완료");
				//	$('#errorPw').show(); // 왜 삭제 완료에서 뜨는거야?????!!
				//=> fail, done은 페이지 상태 (404, 405, 200 등)에 따라서 구분됨
				//	컨트롤러에서 페이지 상태를 받아야함. 리턴타입 : ResponseEntity<String>
				$("#memberDeleteModal").modal('hide');
				$("#memberDeleteSuccessModal").modal('show');
			});
		});
	});
</script>


<script>

/*insert*/
	var ConquestService = (function() {
		function addConquest(data, callback, error) {
			console.log(JSON.stringify(data));
			console.log(data);
			$.ajax({
				type : "post",
				url : appRoot + "/Conquest/addConquest", // 컨트롤러 매핑
				data : data, // form data를 json
				success : function(result, stauts, xhr) {
					if (callback) {
						callback(result);
					}
				},
				error : function(xhr, status, er) {
					if (error) {
						error(er);
					}
				}
			});
		}
		return {
			addConquest : addConquest
		};
	})();
	/* 카운트 ajax로 데이터 보내기 */
	$(document).ready(function() {
		//$("#addConquest").serializeArray();
		$("#add-btn").click(function(e) {
			e.preventDefault();
			ConquestService.addConquest($("#addConquest").serialize());
		});
	});

	
	/*update*/

		function updateConquest(data, callback, error) {
			console.log(JSON.stringify(data));
			console.log(data);
			
			$.ajax({
				type : "post",
				url : (conquestcnt == 0) ? appRoot + "/Conquest/addConquest" : appRoot + "/Conquest/updateConquest", // 컨트롤러 매핑
				contentType: "application/json",// 이 타입으로 data를 보내겠다 컨트롤러에
				data : (conquestcnt == 0) ? {"member_no": member_no,"mountain_no":mountain_no, "conquestcnt":conquestcnt} : {"conquestcnt": conquestcnt}, // form data를 json
				success : function(result, stauts, xhr) {
					if (callback) {
						callback(result);
					}
				},
				error : function(xhr, status, er) {
					if (error) {
						error(er);
					}
				}
			});
		}
	

	/* 카운트 ajax로 데이터 보내기 */
	$(document).ready(function() {
		//$("#addConquest").serializeArray();
		$("#up-btn").click(function(e) {
			e.preventDefault();
			ConquestService.updateConquest($("#updateConquest").serialize());
		});
	});
	
	
	/* 정복산 count */
	function Count(type, ths) {
		var $input = $(ths).parents("td").find("input[name='plusminus']");// name을 찾아 $input에 넣음
		var CCCount = Number($input.val()); //Number타입변환
		var MCount = Number($(ths).parents("tr").find("td.maxconquest").html());//maxconquest찾음
		if (type == 'p') { //plus약자
			if (CCCount < MCount)
				$input.val(Number(CCCount) + 1);// MAX값보다 작을때 +
		} else {
			if (CCCount > 0)
				$input.val(Number(CCCount) - 1); //입력값이 0이상일경우에 -
		}
	}
	
	
	
	/*1번  작업실패
	function bughansan_info() {
	 var mountain_no = "<c:out value='${mountain_no}'/>";
	 console.log(mountain_no);
	 var img_src;
	 if(mountain_no == 291) {
	 img_src="${root }/resources/img/conquest/mountain_black.png";
	 }
	 return img_src;
	 }
	
	 function dobongsan_info() {
	 var mountain_no = "<c:out value='${mountain_no}'/>";
	 if(mountain_no == 740) {
	 img_src = "${root }/resources/img/conquest/mountain_blue.png";
	 }
	 return img_src;
	 } */
</script>

<title>산산산</title>

<style>
/* 산정복 효과 */
figure {
	width: 100%;
	position: relative;
}
figure img {
	display: block;
	width: 100%;
	height: auto;
}
figure h4 { /* 사진 위에 뜨는 텍스트공간  */
	position: absolute;
	top: calc(100% - 50px);
	left: 0;
	width: 100%;
	height: 50px;
	color: #fff;
	background: rgba(11, 156, 49, 0.6); /*  그린rgba */
	margin: 0;
}
figure .overlay {
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
	overflow: hidden;
	width: 100%;
	height: 0;
	color: white;
	background: rgba(11, 156, 49, 0.6); /*  그린rgba */
	-webkit-transition: .6s ease;
	transition: .6s ease;
}
figure .overlay .description {
	font-size: 20px;
	position: absolute;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
	text-align: center;
}
figure:hover h4 {
	display: none;
}
figure:hover .overlay {
	display: block;
	height: 100%;
}
</style>

</head>
<body>
	<m:topNav />

	<!--내 정보 보기 -->
	<form>
		<div class="form-group row">
			<label for="staticId" class="col-sm-2 col-form-label">아이디</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="staticId" value="${sessionScope.authUser.id }">
			</div>
		</div>
		<div class="form-group row">
			<label for="staticPassword" class="col-sm-2 col-form-label">비밀번호</label>
			<div class="col-sm-10">
				<input type="password" readonly class="form-control-plaintext"
					id="staticPassword" value="${sessionScope.authUser.name }">
			</div>
		</div>
		<div class="form-group row">
			<label for="staticName" class="col-sm-2 col-form-label">이름</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="staticName" value="${sessionScope.authUser.name }">
			</div>

		</div>
		<div class="form-group row">
			<label for="staticNickname" class="col-sm-2 col-form-label">닉네임</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="staticNickname" value="${sessionScope.authUser.nickname }">
			</div>
		</div>
		<div class="form-group row">
			<label for="staticEmail" class="col-sm-2 col-form-label">이메일</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="staticEmail" value="${sessionScope.authUser.email }">
			</div>
		</div>
		<div class="form-group row">
			<label for="staticLoc" class="col-sm-2 col-form-label">주소</label>
			<div class="col-sm-10">
				<input type="text" readonly class="form-control-plaintext"
					id="staticLoc" value="${authUser.loc }">
			</div>
		</div>

	</form>

	<h3>산킷리스트</h3>


	<!-- ##수정 버튼 -->
	<a href="${root }/member/myModify">
		<button type="submit" class="btn btn-primary">회원 정보 수정</button>
	</a>

	<%-- 	<!-- ##탈퇴 버튼 -->
	<a href="/member/delete">
		<button type="submit" id="memberDelete" class="btn btn-primary" data-userId="${authUser.id }">회원 탈퇴</button>
	</a> --%>

	<!-- ##탈퇴 버튼 - 모달 -->
	<button type="submit" class="btn btn-primary" data-toggle="modal"
		data-target="#memberDeleteModal" data-whatever="@mdo">회원 탈퇴
		모달</button>
	//#은 아래 모달창을 참조한다는 것! //@는 뭐지?

	<div class="modal fade" id="memberDeleteModal" tabindex="-1"
		aria-labelledby="memberDeleteModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalTitle">진심이세요?</h5>
					<button id="memberDeleteCancel1" type="button" class="close"
						data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="pwConfirm" class="col-form-label">비밀번호 확인</label> <input
								type="password" class="form-control" id="pwConfirm"> <span
								class="form-text" style="color: tomato" id="pwError">
								비밀번호가 일치하지 않습니다. </span> <span class="form-text" style="color: tomato"
								id="pwNull"> 비밀번호를 입력해주세요. </span>
						</div>
						<div class="form-group"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="memberDeleteCancel2">취소</button>
					<button type="button" class="btn btn-primary" id="memberDelete"
						data-userId="${authUser.id }">탈퇴</button>

				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="memberDeleteSuccessModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalTitle">탈퇴하였습니다.</h5>
				</div>
				<div class="modal-body">
					// ##탈퇴 이유 - 나중에 없애도됨! <br> <label for="message-text"
						class="col-form-label">탈퇴하는 이유가 무엇인가요?</label> <br> <select
						id="select">
						<option value="" disabled selected>홈페이지 개선에 도움을 주세요.</option>
						<option value="그냥1" id="go1">그냥1</option>
						<option value="그냥그냥2" id="go2">그냥그냥2</option>
						<option value="그냥그냥그냥3" id="go3">그냥그냥그냥3</option>
						<option value="directly" id="go4">직접 입력하기</option>
					</select>

				</div>
				<div class="modal-footer">
					<a href="${root }/index.jsp">
						<button type="button" class="btn btn-secondary">닫기</button>
					</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 정복 산 리스트 -->
	<p>카운트확인!!!!!!!${list.size() }</p>
	
	<form action='<c:url value="Conquest/updateConquest" />'method="post" id="updateConquest">
								<input hidden="hidden" name="conquestcnt" value="${list.size() }"></input>
								<button id="up-btn" type="submit"> update </button>
							</form><!-- test용버튼  -->
	

	<!-- row 방향으로 가로 배열할 때, 중앙 정렬  -->
	
	
		<div class="container">
	<div class="row d-flex flex-row justify-content-center">
			
		<c:forEach items="${list }" var="conq">
	<div>
				<figure>
				
					<img src='<c:out value="${root }/resources/img/conquest/${conq.mname}.png"/>' width="150" 
						class="img-responsive img-rounded" /><!-- items에 서버단에서 DB연동결과물 request.setAttribute("키값명",저장객체) 와야함 -->
						           
					<h4>${conq.mname } 도장깨기</h4>
					<div class="overlay">
						<div class="description">
						
							<form action='<c:url value="Conquest/addConquest" />'
									method="post" id="addConquest">
									
									<table>
										<tr>
											<td hidden="hidden">정복최대횟수</td>
											<td class="maxconquest" hidden="hidden">100000000000</td>
											<td>
												<input hidden="hidden" name="member_no" value="${authUser.no }"></input>
												<input hidden="hidden" name="mountain_no" value="${conq.mountain_no }"></input>
												<button id="add-btn" name="plusminus" type="button" class="btn btn-outline-success"
													onclick="Count('p',this);" type="submit">정복!!!!</button>
												<input type="text" name="conquestcnt" value="${conq.conquestcnt }" readonly="readonly" style="text-align: center;" />
												<button name="plusminus" type="button" onclick="Count('m', this);" 
													class="btn btn-outline-success" type="submit">잘못눌렀네,,</button>
											</td>
										</tr>
									</table>			
	
							</form>
								
							<form action='<c:url value="Conquest/updateConquest" />'method="post" id="updateConquest">
								<input hidden="hidden" name="conquestcnt" value="${conq.conquestcnt }"></input>
								<button id="up-btn" type="submit"> update </button>
							</form>
						
						</div>
					</div>
				
					
				</figure>
			</div>
				</c:forEach>
				
		</div>
</div>





<%-- 만약 이거 에러나도 안 버려도 되는 코드고 이대로 가면 되고
ㄱ두래!ㅏ등록할 때 똑같이 List<conqStickerVO> 객체 list로 보내면 잘 읽혀질 거예여
그리고 등록은 잘 되는데 업뎃된 정보가 여기에 안 보인다 -> ajax 후에 성공하면 돌리는 코드에서 이 jsp 조작해야 됨넹넹
 --%>

<hr>

 
<%-- 
	<div class="container">
		<div class="row d-flex flex-row justify-content-center">
			<!-- row 방향으로 가로 배열할 때, 중앙 정렬  -->

			<div>
				<figure>
					<img src="${root }/resources/img/conquest/bughansan.png"
						class="img-responsive img-rounded" />
					<h4>북한산 도장깨기</h4>

					<div class="overlay">
						<div class="description">
							<!-- 	스티커이미지 추가추가추가~ -->
							<!-- 		<button name="conquest_btn" type="button" id="addConquest_btn1"
								class="btn btn-outline-success">정복한 산!!</button> -->
							<form action='<c:url value="Conquest/addConquest" />'
								method="post" id="addConquest">
								<table>

									<tr>
										<td hidden="hidden">정복최대횟수</td>
										<td class="maxconquest" hidden="hidden">100000000000</td>
										<td><input hidden="hidden" name="member_no"
											value="${authUser.no }"></input> <input hidden="hidden"
											name="mountain_no" value="291"></input>
											<button id="btn-1" name="conquestcnt" type="button"
												onclick="Count('p',this);" class="btn btn-outline-success"
												type="submit">정복!!!!</button> <input type="text"
											name="conquestcnt" value="1" readonly="readonly"
											style="text-align: center;" />
											<button name="conquestcnt" type="button"
												onclick="Count('m', this);" class="btn btn-outline-success"
												type="submit">잘못눌렀네,,</button></td>
									</tr>
								</table>


								<!--  1.북한산 291
2. 도봉산 740
3. 수락산 44
4. 인왕산 294
5. 아차산 297
6. 관악산 61 -->
							

							</form>
							<form action='<c:url value="Conquest/updateConquest" />'method="post" id="updateConquest">
							<input hidden="hidden" name="conquestcnt" value=""></input>
							<button id="up-btn" type="submit"> update </button>
							</form>
							
									초반 script사용소스	 <img id="bughansan"
								src="${root }/resources/img/conquest/mountain_black.png">
							<script>
									document.getElementById('bughansan').src = bughansan_info()
								</script> 
						</div>
					</div>
				</figure>

			</div>

 
			<div>
				<figure>
					<img src="${root }/resources/img/conquest/dobongsan.png"
						class="img-responsive img-rounded" />
					<h4>도봉산 도장깨기</h4>
					<div class="overlay">
						<div class="description"> 스티커이미지 추가추가추가~
							스크립트로 표현하는경우<img id="title" src="">
							<script>
								document.getElementById('title').src = title_info()
							</script>

						</div>

					</div>

				</figure>
			</div>

			<div>
				<figure>
					<img src="${root }/resources/img/conquest/sulagsan.png"
						class="img-responsive img-rounded" />
					<h4>수락산 도장깨기</h4>
					<div class="overlay">
						<div class="description">스티커이미지 추가추가추가~</div>
					</div>
				</figure>

			</div>
			<div>
				<figure>
					<img src="${root }/resources/img/conquest/in-wangsan.png"
						class="img-responsive img-rounded" />
					<h4>인왕산 도장깨기</h4>
					<div class="overlay">
						<div class="description">스티커이미지 추가추가추가~</div>
					</div>
				</figure>

			</div>
			<div>
				<figure>
					<img src="${root }/resources/img/conquest/achasan.png"
						class="img-responsive img-rounded" />
					<h4>아차산 도장깨기</h4>
					<div class="overlay">
						<div class="description">스티커이미지 추가추가추가~</div>
					</div>
				</figure>

			</div>
			<div>
				<figure>
					<img src="${root }/resources/img/conquest/gwan-agsan.png"
						class="img-responsive img-rounded" />
					<h4>관악산 도장깨기</h4>
					<div class="overlay">
						<div class="description">스티커이미지 추가추가추가~</div>
					</div>
				</figure>

			</div>
 
		</div>
	</div> 
	
	 --%>
	


</body>
</html>