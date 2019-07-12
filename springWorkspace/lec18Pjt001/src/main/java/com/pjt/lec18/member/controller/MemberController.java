package com.pjt.lec18.member.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pjt.lec18.member.Member;
import com.pjt.lec18.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	//MemberService service = new MemberService();
	//@Autowired
	@Resource(name="memService")
	MemberService service;


	//GET방식일 경우 : @RequestMapping(value="/memJoin", method=RequestMethod.GET)
	//GET방식일 경우(생략 가능) : @RequestMapping(value="/memJoin")
	//value속성이 1개인 경우 : @RequestMapping("/memJoin", method=RequestMethod.POST)
	/*@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(Model model, HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		String memMail = request.getParameter("memMail");
		String memPhone1 = request.getParameter("memPhone1");
		String memPhone2 = request.getParameter("memPhone2");
		String memPhone3 = request.getParameter("memPhone3");

		service.memberRegister(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);

		model.addAttribute("memId", memId);
		model.addAttribute("memPw", memPw);
		model.addAttribute("memMail", memMail);
		model.addAttribute("memPhone", memPhone1 + "-" + memPhone2 + "-" + memPhone3);

		return "memJoinOk";
	}*/


	//Command 객체를 이용 : 객체의 getter,setter무조건 존재
	/*@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(Member member) {
		service.memberRegister(member.getMemId(), member.getMemPw(), member.getMemMail(), member.getMemPhone1(), member.getMemPhone2(), member.getMemPhone3());

		return "memJoinOk";
	}*/

	//Command 객체를 이용 + Command객체의 이름을 변경
	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public String memJoin(@ModelAttribute("mem") Member member) {
		service.memberRegister(member);

		return "memJoinOk";
	}


	//HttpServletRequest 사용
	/*@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String memLogin(Model model, HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");

		Member member = service.memberSearch(memId, memPw);

		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "memLoginOk";
	}*/

	//Annotation @RequestParam 사용
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String memLogin(Model model,
						@RequestParam("memId") String memId,
						@RequestParam("memPw") String memPw) {
		Member member = service.memberSearch(memId, memPw);

		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "memLoginOk";
	}
}
