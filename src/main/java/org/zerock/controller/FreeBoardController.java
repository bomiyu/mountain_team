package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.freeboard.FCriteria;
import org.zerock.domain.freeboard.FPageDTO;
import org.zerock.domain.freeboard.FreeBoardVO;
import org.zerock.domain.member.MemberVO;
import org.zerock.service.freeboard.FreeBoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@RequestMapping("/freeboard/*")
@Log4j
public class FreeBoardController {

	private FreeBoardService service;

//	public BoardController(FreeBoardService service) {
//		super();
//		this.service = service;
//	}

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	@GetMapping("/list")
//
//	public void list(Model model) {
//		log.info("******************** list *******************");
//		List<FreeBoardVO> list = service.getList();
//		model.addAttribute("list", list);
//	}

	@GetMapping("/list")
	public void list(@ModelAttribute("cri") FCriteria cri, @ModelAttribute("vo")FreeBoardVO vo, @ModelAttribute("User") MemberVO User, Model model) {
		model.addAttribute("list", service.getList(cri));
		int total = service.getTotal(cri);
			
		log.info("total:::::::::" + total);
		model.addAttribute("pageMaker", new FPageDTO(cri, total));
		model.addAttribute("cnt", total);
		
	}

	@GetMapping("/register")
	@RequestMapping("/register")
	public void register(FreeBoardVO vo) {

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PostMapping("/register")
	public String register(@ModelAttribute("vo") FreeBoardVO vo, Model model, RedirectAttributes rttr, HttpSession session) {
		// Session에 저장된 nickname을 User_nickname에저장
		MemberVO User =(MemberVO) session.getAttribute("authUser");
		vo.setUser_nickname(User.getNickname());
		System.out.println("유저의 닉네임은"+vo.getUser_nickname());
		/*
		 * //값셋팅확인 String nick = vo.getUser_nickname(); log.info("nick셋팅은???"+nick);
		 */
		
//		log.info(User.getName());
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getNo());
		rttr.addFlashAttribute("message", vo.getUser_nickname() + "님의 글이 등록되었습니다.");


		return "redirect:/freeboard/list";
	}

	@GetMapping({ "/get", "/modify" })
	public void get(@RequestParam("no") Long no, @ModelAttribute("cri") FCriteria cri, Model model) {
		log.info("get method - no: " + no);
		FreeBoardVO vo = service.get(no);
		model.addAttribute("freeboard", vo);
		
		/* 닉네임 test
		 * FreeBoardVO vo2= new FreeBoardVO(); String nicks= vo2.getWriter();
		 * model.addAttribute("nicks", nicks); log.info("nicks: " + nicks);
		 */
		
		
//		FCriteria cri = new FCriteria();
//		model.addAttribute("cri", cri);//위의 ModelAttribute와 같은것 !

	}

	@PostMapping("/modify")
	public String modify(FreeBoardVO vo, @ModelAttribute("cri") FCriteria cri, RedirectAttributes rttr) {
		
		if (service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", vo.getNo() + "번 글이 수정되었습니다.");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
//	 조회페이지에서의 검색처리 (수정,삭제는 컨트롤러에 redirect 방식으로 동작하므로 type, keyword 조건 같이 rttr에 포함
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/freeboard/list";
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("no") Long no, @ModelAttribute("cri") FCriteria cri, RedirectAttributes rttr) {

		if (service.remove(no)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", no + "번 글이 삭제되었습니다.");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/freeboard/list";
	}

}
