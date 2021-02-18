package org.zerock.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.conquest.ConquestVO;
import org.zerock.service.conquest.ConquestService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@RequestMapping("/Conquest/*")
@Log4j
public class ConquestController {

	private ConquestService service;

	// 산 정복 담기
	@ResponseBody //jsp에서 Ajax 사용하기위해 씀
	@RequestMapping(value = "/addConquest", method = RequestMethod.POST)
	public void addConquest(ConquestVO cvo, HttpSession session) throws Exception {

		ConquestVO User = (ConquestVO) session.getAttribute("authUser");
		cvo.setMember_no(User.getNo());

		service.addConquest(cvo);

	}

}
