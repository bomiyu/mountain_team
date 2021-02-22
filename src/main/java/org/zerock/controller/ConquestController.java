package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.conquest.ConquestVO;
import org.zerock.domain.mountain.ConqStickerVO;
import org.zerock.service.conquest.ConquestService;
import org.zerock.service.mountain.MountainService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/Conquest/*")
@Log4j

public class ConquestController {

	@Setter(onMethod_ = @Autowired)
	private ConquestService service;

	@Setter(onMethod_ = @Autowired)
	private MountainService mservice;

	/*
	 * @PostMapping(value = "/addConquest") public ResponseEntity<String>
	 * addConquest(@ModelAttribute("cvo") ConquestVO cvo, Model model) {
	 * 
	 * log.info("vo: " + cvo);
	 * 
	 * int insertCount = service.addConquest(cvo);
	 * 
	 * 
	 * 
	 * log.info("count: " + insertCount);
	 * 
	 * if (insertCount == 1) { return new ResponseEntity<> ("success",
	 * HttpStatus.OK); } else { return new ResponseEntity<>
	 * (HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

	@PostMapping(value = "/updateConquest")
	// consumes: 이 타입만 서버에서 받겠다, @RequestBody: request의 body를 자바 객체로 변환해서 서버에서 쓰겠다
	public ResponseEntity<List<ConqStickerVO>> updateConquest(ConquestVO cvo) {
		// 파라미터로 들어와는(Request의 body부분에 해당:어노테이션) conquestvo의 정보:
		// (어떤 멤버)가 (어떤 산)을 (정복한 횟수)를 수정하겠다
		log.info("UPvo: " + cvo);
		
//		MountainVO mvo = mservice.get(cvo.getMountain_no());
		//파라미터로 받은 cvo(업데이트 시킬 내용)에서 업데이트 시킬 mountain_no를 받아서 그 마운틴VO를 get 그넘버에 대한값을받겟다 그런건가?
		
			if(service.updateConquest(cvo)) {// 그냥 cvo 보내서 update하고, 잘 업뎃됐으면 ok 보내주면 됨
				List<ConqStickerVO> list = mservice.getConqListbyMem(cvo.getMember_no());// 이 리스트를 보내도 되나,,,?
				
				log.info(list);
				
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
			}

	}
	
}