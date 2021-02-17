package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.nreply.NReplyVO;
import org.zerock.service.nreply.NReplyService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/nreplies/*")
public class NReplyController {// using ajax!

	private NReplyService service;
	
	@PostMapping(value = "/new",
				 consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
				 produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> register(@RequestBody NReplyVO reply) {
		if(service.register(reply) == 1) {
			return new ResponseEntity<String>("newReplySuccess", HttpStatus.OK);			
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/{no}",
				 consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
				 produces = MediaType.TEXT_PLAIN_VALUE)// 필요?
	public ResponseEntity<String> modify(@RequestBody NReplyVO reply){
		if(service.modify(reply)) {
			return new ResponseEntity<>("modReplySuccess", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping(value = "/{no}",
				   produces = MediaType.TEXT_PLAIN_VALUE)// 꼭 produce 써줘야 delReplySuccess가 넘어가나?
	public ResponseEntity<String> remove(@PathVariable Long no){
		if(service.remove(no)) {
			return new ResponseEntity<>("delReplySuccess", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/list/{notice_no}",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<NReplyVO>> list(@PathVariable Long notice_no){// 기준 클래스 만들어야 할 듯 -> total, ...
		//int total = service.getTotal(notice_no);// 내릴 때마다 10개씩 로딩하고 싶은데 -> js
		List<NReplyVO> list = service.getList(notice_no);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
