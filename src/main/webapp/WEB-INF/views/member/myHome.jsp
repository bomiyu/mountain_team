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

<title>Insert title here</title>

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
	<p class="addConquest">
	<div class="container">
		<div class="row d-flex flex-row justify-content-center">
			<!-- row 방향으로 가로 배열할 때, 중앙 정렬  -->

			<div>
				<figure>
					<img src="${root }/resources/img/conquest/bughansan.png"
						class="img-responsive img-rounded" />
					<h4>북한산 도장깨기</h4>
					<div class="overlay">
						<div class="description">스티커이미지 추가추가추가~</div>
					</div>
				</figure>

			</div>
			<div>
				<figure>
					<img src="${root }/resources/img/conquest/dobongsan.png"
						class="img-responsive img-rounded" />
					<h4>도봉산 도장깨기</h4>
					<div class="overlay">
						<div class="description">스티커이미지 추가추가추가~</div>
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



	<script>
		$(".addCart_btn").click(function() {
			var gdsNum = $("#gdsNum").val();
			var cartStock = $(".numBox").val();

			var data = {
				gdsNum : gdsNum,
				cartStock : cartStock
			};

			$.ajax({
				url : "/shop/view/addCart",
				type : "post",
				data : data,
				success : function(result) {
					alert("카트 담기 성공");
					$(".numBox").val("1");
				},
				error : function() {
					alert("카트 담기 실패");
				}
			});
		});
	</script>
	</p>




</body>
</html>