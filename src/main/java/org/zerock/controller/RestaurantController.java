package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.member.MemberVO;
import org.zerock.domain.restaurant.AddressVO;
import org.zerock.domain.restaurant.Rcriteria;
import org.zerock.domain.restaurant.RestaurantVO;
import org.zerock.domain.restaurant.RpageDTO;
import org.zerock.service.restaurant.RestaurantService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/restaurant/*")
@AllArgsConstructor
@Log4j
public class RestaurantController {
	private RestaurantService service;

	@GetMapping("/list")
	public void list(Model model, @ModelAttribute("cri") Rcriteria cri) {
		List<RestaurantVO> list = service.getList(cri);
		// userno 세션 가져오기
		// int userno = (authUser)
		// req.getSession().getAttribute("authUser").getUserno();
		int total = service.getTotal(cri);
		RpageDTO dto = new RpageDTO(cri, total);
		log.info("*****************" + list + "********************");
		model.addAttribute("list", list);
		model.addAttribute("page", dto);

	}

	@PostMapping("/register")
	public String register(RestaurantVO restaurant, BindingResult result, RedirectAttributes rttr, AddressVO addr, HttpSession session) throws Exception {
		// manager == 1 세션 가져오기
		// User authUser = (User) req.getSession().getAttribute("authUser");
//		MemberVO user = (MemberVO) session.getAttribute("authUser");
		
		log.info("*************** m.name ***********" + restaurant.getMname() + "******************************");
		MemberVO user = (MemberVO) session.getAttribute("authUser");
		
		if (user.getManager() == 1) {
			String address = addr.getAddress1() + " " + addr.getAddress2();
			log.info("**************************" + address + "******************************");
			restaurant.setRloc(address);
			log.info("**************************" + restaurant + "******************************");
			service.register(restaurant);
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", restaurant.getNo() + "번 글이 등록되었습니다");
		}

		return "redirect:/restaurant/list";
	}


	@GetMapping("/register")
	public String register(@ModelAttribute("cri") Rcriteria cri, RedirectAttributes rttr, HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("authUser");
		try {
		if(user.getManager() == 0 || user == null) {
			rttr.addFlashAttribute("message2", "관리자만이용 가능합니다");
			return "redirect:/restaurant/list";
		}
		} catch (NullPointerException e) {
			e.printStackTrace();
			rttr.addFlashAttribute("message2", "관리자만이용 가능합니다");
			return "redirect:/restaurant/list";
		}
		return "/restaurant/register";
	}

	@GetMapping("/remove")
	public String remove(RedirectAttributes rttr) {
		rttr.addFlashAttribute("message2", "페이지가 존재하지 않습니다");
		return "redirect:/restaurant/list";
	}

	
	@GetMapping("/modify")
	public String get(Long no, Model model, @ModelAttribute("cri") Rcriteria cri,RedirectAttributes rttr, HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("authUser");
		try {
		if(user.getManager() == 0 || user == null) {
			rttr.addFlashAttribute("message2", "관리자만이용 가능합니다");
			return "redirect:/restaurant/list";
		}
		} catch (NullPointerException e) {
			e.printStackTrace();
			rttr.addFlashAttribute("message2", "관리자만이용 가능합니다");
			return "redirect:/restaurant/list";
		}
		RestaurantVO vo = service.read(no);
		log.info("********* modify get *************" + vo.getRloc() + "*******************");

		model.addAttribute("restaurant", vo);
		return "/restaurant/modify";
	}

	@PostMapping("/remove")
	// manager 세션 가져오기
	public String remove(Long no, RedirectAttributes rttr, Rcriteria cri, HttpSession session) throws Exception {
//		if(service.remove(no)) {
//			rttr.addFlashAttribute("result", "success");
//			rttr.addFlashAttribute("message", no + "번 글이 삭제되었습니다");
//		}
//		MemberVO user = (MemberVO) session.getAttribute("authUser");
//		if (user.getManager() == 1) {
			service.remove(no);
//		}
			rttr.addAttribute("pageNo", cri.getPageNo());
			rttr.addAttribute("amount", cri.getAmount());
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/restaurant/list";
	}

	@PostMapping("/modify")
	// manager 세션 가져오기
	public String modify(RestaurantVO restaurant, RedirectAttributes rttr, Rcriteria cri, HttpSession session,
			AddressVO addr) throws Exception {
		String address = addr.getAddress1() + " " + addr.getAddress2();
		log.info(restaurant);
		log.info("****************    address   **********" + address + "******************************");
//		MemberVO user = (MemberVO) session.getAttribute("authUser");
//		if (user.getManager() == 1) {

			restaurant.setRloc(address);

			if (service.modify(restaurant)) {
				rttr.addFlashAttribute("result", "success");
				rttr.addFlashAttribute("message", restaurant.getNo() + "번 글이 수정되었습니다");
			}
//		}
		rttr.addAttribute("pageNo", cri.getPageNo());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/restaurant/list";
	}

}
