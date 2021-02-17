package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.member.MemberVO;
import org.zerock.domain.notice.NCriteria;
import org.zerock.domain.notice.NPageDTO;
import org.zerock.domain.notice.NoticeVO;
import org.zerock.service.notice.NoticeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor// 필드 주입
@RequestMapping("/notice/*")
public class NoticeController {

	private NoticeService service;
	
	/* session-member test
	@GetMapping("/register")
	@RequestMapping("/register")
	public void register(HttpServletRequest req) {
		// /notice/register.jsp
		
		MemberVO member = new MemberVO();
		member.setNo(1);
		member.setName("hong");
		
		HttpSession session = req.getSession();
		session.setAttribute("user", member);
	}
	*/
	
	@GetMapping("/register")
	public String register(HttpSession session) {
		// 로그인 체크
		Object user = session.getAttribute("authUser");
		if (user == null) {
			// 로그인 창으로, alert('관리자만 접근할 수 있습니다.')
			return "redirect:/member/login";
		} else if (((MemberVO) user).getManager() == 0) {
			// alert('관리자만 접근할 수 있습니다.')
			return "redirect:/notice/list";
		}
		return "/notice/register";
		// /notice/register.jsp
	}
	
	@PostMapping("/register")
	public String register(NoticeVO notice) {// (category), title, content
		// spring이 request.getParameter() 처리
		service.register(notice);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/get")
	public void get(Long no, Model model, @ModelAttribute("cri") NCriteria cri) {
		NoticeVO notice = service.getWithCnt(no);
		model.addAttribute("notice", notice);
		// /notice/get.jsp
	}
	
	/* using ajax : update cnt */
	/*@GetMapping("/cnt/{no}")
	public ResponseEntity<String> modifyCnt(@PathVariable Long no){
		if (service.modifyCnt(no)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@GetMapping("/modify")
	public void modify(Long no, Model model, @ModelAttribute("cri") NCriteria cri) {
		NoticeVO notice = service.get(no);
		model.addAttribute("notice", notice);
		// /notice/modify.jsp
	}
	
	/*
	@PostMapping("/modify")
	public String modify(NoticeVO notice, RedirectAttributes rttr) {
		if (service.modify(notice)) {
			rttr.addFlashAttribute("result", "modSuccess");// parameter로 붙음
		}
		rttr.addAttribute("no", notice.getNo());
		return "redirect:/notice/get";// param -> no지정		
	}*/
	
	/* using ajax : modify */
	@PostMapping(value = "/modify",
				 consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> modify(@RequestBody NoticeVO notice, HttpSession session){
		if (service.modify(notice)) {
			session.setAttribute("result", "modSuccess");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*
	@PostMapping("/remove")
	public String remove(Long no, RedirectAttributes rttr) {
		if (service.remove(no)) {
			rttr.addFlashAttribute("result", "delSuccess");
		}
		return "redirect:/notice/list";
	}*/
	
	/* using ajax : remove */
	@DeleteMapping("/remove/{no}")
	public ResponseEntity<String> remove(@PathVariable Long no, HttpSession session) {// 주소창에서 no 받아서 처리
		if (service.remove(no)) {
			session.setAttribute("result", "delSuccess");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/list")
	public void list(Model model, @ModelAttribute("cri") NCriteria cri) {// cri -> setter()		
		List<NoticeVO> list = service.getList(cri);// amount: 10개씩만 갖고 오기
		model.addAttribute("list", list);// disaptcherServlet이 모델 관리, jsp한테 넘겨줌
		
		int total = service.getTotal(cri);
		model.addAttribute("pages", new NPageDTO(total, cri));
	}
	
}
