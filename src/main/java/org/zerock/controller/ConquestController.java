package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.conquest.ConquestVO;
import org.zerock.service.conquest.ConquestService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/Conquest/*")
@Log4j

public class ConquestController {

	@Setter(onMethod_=@Autowired)
	private ConquestService service;
	
	@PostMapping(value = "/addConquest")
	public ResponseEntity<String> addConquest(ConquestVO cvo) {
		
		log.info("vo: " + cvo);
		
		int insertCount = service.addConquest(cvo);
		
		log.info("count: " + insertCount);
		
		if (insertCount == 1) {
			return new ResponseEntity<> ("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
/*실패 컨트롤러작성	// 산 정복 담기
	 //메서드 작성하기 @Model 어노테이션을 통해 testPage 생성
	@RequestMapping(value = "/addConquest", method = RequestMethod.POST)
	public void addConquest(ConquestVO cvo, HttpSession session,Model model) throws Exception {
		ConquestVO User = (ConquestVO) session.getAttribute("authUser");
		cvo.setMember_no(User.getNo()); //유저넘버 셋팅
		
		
	System.out.println(cvo.toString());
	service.addConquest(cvo);

	
	
//	
//	model.addAttribute("cvo", service.getNo());
	}

	*/
	
	
	
	
}
