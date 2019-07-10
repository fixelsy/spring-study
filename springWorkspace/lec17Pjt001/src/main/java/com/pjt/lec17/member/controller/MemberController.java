package com.pjt.lec17.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pjt.lec17.member.Member;
import com.pjt.lec17.member.service.MemberService;

@Controller
public class MemberController {

	//서비스 객체 구현 1)new 연산자를 이용한 service 객체 생성 및 참조
	//MemberService service = new MemberService();;

	//서비스 객체 구현 2)스프링 설정파일을 이용한 서비스 객체 생성 및 의존 객체 자동 주입
	/*@Autowired
	MemberService service;*/

	//서비스 객체 구현 3-1)annotation을 이용해서 서비스 객체 생성 및 의존 객체 자동 주입 -> MemberService.class
	//서비스 객체 구현 3-2)
	@Resource(name="memService")
	MemberService service;

	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
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
		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);

		return "memJoinOk";
	}

	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public String memLogin(Model model, HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");

		Member member =service.memberSearch(memId, memPw);

		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "memLoginOk";
	}
}
