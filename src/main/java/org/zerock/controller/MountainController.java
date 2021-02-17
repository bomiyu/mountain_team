
package org.zerock.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.mountain.MountainVO;
import org.zerock.service.mountain.MountainService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class MountainController {

	private MountainService service;
	
	// 산 등록
	@PostMapping("/register")
	public String register(MountainVO mountain,RedirectAttributes rttr) {
		
		service.register(mountain);
		
		log.info("register POST...");
	    
		//등록 성공시 
		rttr.addFlashAttribute("result",mountain.getNo());
		rttr.addFlashAttribute("confirm", mountain.getNo() + "글 등록 성공!");
		
		//등록이 끝난 후 목록으로
		return "redirect:/board/list";
	}
	
	// 산등록
	@GetMapping("/register")
	public void register() {
		
	}

	// 산 리스트
	@GetMapping("/list")
	public void list(Model model) {
		
		log.info("list");
		
		model.addAttribute("list", service.getList());
		
		
	}
	
	// 산 조회 
	@GetMapping("/get")
	public void get(Long no, Model model) {
		log.info("/get");
		model.addAttribute("mountain", service.get(no));
	}
	/*
	// 산 수정
	@GetMapping("/modify")
		public String modify(Long no, Model model) {
		    MountainVO vo = service.get(no);
		    model.addAttribute("mountain", vo);
			return "redirect:mountain/list";
		}
	
	
	// 산읽기(/명소/축제)
		@PostMapping("/read")
		public void read(FestivalVO festival, Model model) {
			
			log.info("/read");
			model.addAttribute("mountain", service.getList());
			
		}
	*/
	// 산 수정(post)
	@PostMapping("/modify")
	public String modify(MountainVO mountain, RedirectAttributes rttr) {
		log.info("modify:" + mountain);
		
		if(service.modify(mountain)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", "수정 완료");
		}
		
		return "redirect:/board/list";
	}
	
	
	// 산 삭제
	@PostMapping("/remove")
	public String remove(Long no, RedirectAttributes rttr) {
		
		if(service.remove(no) ) {
			rttr.addAttribute("result", "success");
			rttr.addFlashAttribute("message", "삭제 완료");
			
		}
		return "redirect:/board/list ";
	}

	
}
