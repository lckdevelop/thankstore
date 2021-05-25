package com.thank.store.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thank.store.dto.CvsProductDTO;
import com.thank.store.dto.ManPagingDTO;
import com.thank.store.dto.ManSearchDTO;
import com.thank.store.dto.ManagerDTO;
import com.thank.store.dto.MemberDTO;
import com.thank.store.service.ManagerService;
import com.thank.store.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private MemberService memberService;
	
	
	/*
	 * 작성자: 이채경
	 * 작성일자: 2021/05/23 
	 */
	@GetMapping("/home")
	public String home(@ModelAttribute ManagerDTO managerDTO, 
				       @RequestParam(defaultValue="1") long pg, 
				       @ModelAttribute ManSearchDTO searchDTO,
				       Model model) {
		long managerNo = 1; // 세션 매니저번호
		long cvsNo = 40; // 세션 매니저지점번호
		managerDTO = new ManagerDTO();
		
		try {
			managerDTO = managerService.getManagerInfo(managerNo);
			model.addAttribute("managerDTO", managerDTO);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		searchDTO.setPagingDTO(new ManPagingDTO(pg));
		searchDTO.setCvstore_no(cvsNo);

		try {
			List<CvsProductDTO> allList = managerService.getAllProductList(searchDTO);
			ManPagingDTO pagingDTO = managerService.getPagingInfo(searchDTO);
			model.addAttribute("allList", allList);
			model.addAttribute("pagingDTO", pagingDTO);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return "manager/home";
	}
	
	/*
	 * 작성자: 이채경
	 * 작성일자: 2021/05/24 
	 */
	@GetMapping("/enrolled")
	public String enrolled(@ModelAttribute ManagerDTO managerDTO, 
		       @RequestParam(defaultValue="1") long pg, 
		       @ModelAttribute ManSearchDTO searchDTO,
		       Model model) {
		long managerNo = 1; // 세션 매니저번호
		long cvsNo = 40; // 세션 매니저지점번호
		managerDTO = new ManagerDTO();
		
		try {
			managerDTO = managerService.getManagerInfo(managerNo);
			model.addAttribute("managerDTO", managerDTO);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		searchDTO.setPagingDTO(new ManPagingDTO(pg));
		searchDTO.setCvstore_no(cvsNo);

		try {
			List<CvsProductDTO> enrolledList = managerService.getEnrolledProductList(searchDTO);
			ManPagingDTO pagingDTO = managerService.getPagingInfo(searchDTO);
			model.addAttribute("enrolledList", enrolledList);
			model.addAttribute("pagingDTO", pagingDTO);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return "manager/enrolledPage";
	}
	
	/*
	 * 작성자: 이채경
	 * 작성일자: 2021/05/24
	 */
	@GetMapping("/enroll")
	public String enrollAvail(@ModelAttribute ManagerDTO managerDTO, 
		       @RequestParam(defaultValue="1") long pg, 
		       @ModelAttribute ManSearchDTO searchDTO,
		       Model model) {
		long managerNo = 1; // 세션 매니저번호
		long cvsNo = 40; // 세션 매니저지점번호
		managerDTO = new ManagerDTO();
		
		try {
			managerDTO = managerService.getManagerInfo(managerNo);
			model.addAttribute("managerDTO", managerDTO);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		searchDTO.setPagingDTO(new ManPagingDTO(pg));
		searchDTO.setCvstore_no(cvsNo);

		try {
			List<CvsProductDTO> enrollAvailList = managerService.getEnrolAvaiProductList(searchDTO);
			ManPagingDTO pagingDTO = managerService.getPagingInfo(searchDTO);
			model.addAttribute("enrollAvailList", enrollAvailList);
			model.addAttribute("pagingDTO", pagingDTO);
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
		return "manager/enrollPage";
	}
	
	
	/*
	 * 작성자: 김수빈
	 * 작성일자: 2021/05/24 10:50
	 */
	@GetMapping()
	public String login2() {
		return "/manager/login";
	}
	
	/*
	 * 작성자: 김수빈
	 * 작성일자: 2021/05/24 10:50
	 */
	@GetMapping("/signup")
	public String signup() {
		return "/manager/signup";
	}
	
	
	/*
	 * 작성자: 김수빈
	 * 작성일자: 2021/05/24 16:20
	 * 로그인 시 받아온 MemberDTO값의 no (pk) 를 이용하여 그 pk를 member_no로 가지는 manager 테이블의 no(pk) 로 세션 보내기
	 */
	@PostMapping()
	public String login(@ModelAttribute MemberDTO memberDTO, 
			Model model,
			HttpSession session) {
		log.info(memberDTO.toString());
		try {
			//여기서 memberDTO를 이용해서 맞는 mangerDTO 받아와야함
			int managerNoFromMember= managerService.getManagerNoFromMember(memberDTO);
			ManagerDTO managerInfo = managerService.getManagerInfo(managerNoFromMember);
			session.setAttribute("managerInfo", managerInfo);

			return "redirect:./manager/home";
		} catch (Exception e) {
			log.info(e.getMessage());
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url", "./manager");
			return "result";
		}
	}
	
	/*
	 * 작성자: 김수빈
	 * 작성일자: 2021/05/24 10:50
	 */
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
		session.invalidate();
		
		ModelAndView mav = new ModelAndView("result");
		mav.addObject("msg", memberInfo.getId() + 
				 "(" + memberInfo.getName() + ")님이 로그아웃 하였습니다.");
		mav.addObject("url", "./");
		return mav;
	}
	
	
	/*
	 * 작성자: 김수빈
	 * 작성일자: 2021/05/24 10:50
	 */
	@PostMapping(value="/signup")
	public String signUp(
			@ModelAttribute MemberDTO memberDTO,
			Model model) {
		try {
			if(memberService.checkMemberExist(memberDTO)==0) {
				memberService.addManager(memberDTO);
			}

			return "redirect:./login";
		} catch (Exception e) {
			log.info(e.getMessage());
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url", "./");
			return "result";
		}
	}
}
