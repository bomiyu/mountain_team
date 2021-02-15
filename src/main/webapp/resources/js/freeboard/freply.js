console.log("reply module!!!!!!!!!!!!!");

var FReplyService = (function(){

//댓글 등록
	function register(freply, callback, error){ //freply객체 콜백 전달
		console.log("freply~!~!");
		
		$.ajax({
			type: 'post',
			url: appRoot + '/replies/new',
			data: JSON.stringify(freply), //JavaScript 값이나 객체를 JSON 문자열로 변환
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) { 
					error(er);
				}
			}
		})
		
		
}
//댓글 목록 - freply이라는 객체를 통해서 필요한 파라미터를 전달받아 json목록을 호출!!<< 전달받기위한 객체
function getList(freply, callback, error) {
		var board_no = freply.board_no; 
		var page = freply.page || 1; // page변수에 page또는 1설정
		
		$.getJSON(appRoot + "/replies/pages/" + board_no + "/" + page, function(data) {
			if (callback) {
				callback(data);
			}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}

//댓글 삭제
	function remove(no, callback, error){ //freply객체 콜백 전달
			$.ajax({
			type: 'delete',
			url: appRoot + '/replies/' + no,
			success : function(deleteResult, status, xhr){
				if(callback){
					callback(deleteResult);
					}
			},
			error: function(xhr, status, er) {
				if (error) { 
					error();
				}
			}
		});
}

//댓글 수정
	function update(freply, callback, error){ 
	
			console.log("frelyno:" + freply.no);
			
			$.ajax({
			type: 'put',
			url: appRoot + "/replies/" + freply.no,
			data: JSON.stringify(freply),
			contentType: "application/json; charset=utf-8",
			success : function(updateResult, status, xhr){// 서버에서 결과를 받았을경우 callback이 success가됨 아닐경우 error
				if(callback){
					callback(updateResult);
					}
			},
			error: function(xhr, status, er) {
				if (error) { 
					error();
				}
			}
		});
}

//댓글 조회 
	function get(board_no, callback, error){ 
			$.ajax({
				type: 'get',
				url: appRoot + "/replies/" + board_no + ".json",
				success : function(getResult, status, xhr){
					if(callback){
						callback(getResult);
					}
				},
				error: function(xhr, status, er) {
					if (error) { 
							error();
					}
				}
			});
	}

 return{
 	register: register,
 	getList: getList,
 	remove: remove,
 	update: update,
 	get: get
 };
 
})();

