
var FReplyService = (function() {

	function addConquest(conquest, callback, error) {
		console.log(conquest);
		
		$.ajax({
			type: "post",
			url:  appRoot + "/Conquest/addConquest",  // 컨트롤러 매핑
			data: JSON.stringify(conquest),     // form data를 json
			contentType: "application/json; charset=utf-8",
			success: function(result, stauts, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
				
			}
		});
	}

	
	return {
		addConquest: addConquest
	};
})();

