package com.pjt.lec20.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pjt.lec20.member.Member;
import com.pjt.lec20.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;

	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		return dateFormat.format(date);
	}

	//Join
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinReg(Member member) {
		service.memberRegister(member);

		return "/member/joinOk";
	}


	//Login
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}

	//세션사용 1) HttpServletRequest를 통해 세션을 받음
	/*@RequestMapping(value="/login", method=RequestMethod.POST)
	public String memLogin(Member member, HttpServletRequest request) {

		Member mem = service.memberSearch(member);

		HttpSession session = request.getSession();
		session.setAttribute("member", mem);

		return "/member/loginOk";
	}*/


	//세션사용 2) HttpSession을 통해 바로 세션을 받음
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String memLogin(Member member, HttpSession session) {
		Member mem = service.memberSearch(member);

		session.setAttribute("member", mem);

		return "/member/loginOk";
	}



	//Logout
	//세션사용 1) HttpServletRequest를 통해 세션을 받음
	/*@RequestMapping("/logout")
	public String memLogout(Member member, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();

		return "/member/logoutOk";
	}*/


	//세션사용 2) HttpSession을 통해 바로 세션을 받음
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpSession session) {
		session.invalidate();

		return "/member/logoutOk";
	}

	//Modify
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public ModelAndView modifyForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");

		ModelAndView mav = new ModelAndView();
		mav.addObject("member", service.memberSearch(member));
		mav.setViewName("/member/modifyForm");

		return mav;
	}

	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public ModelAndView modify(Member member, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Member mem = service.memberModify(member);
		session.setAttribute("member", mem);

		ModelAndView mav = new ModelAndView();
		mav.addObject("memAft", mem);
		mav.setViewName("/member/modifyOk");

		return mav;
	}




	//Remove
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");

		ModelAndView mav = new ModelAndView();
		mav.addObject("member", member);
		mav.setViewName("/member/removeForm");

		return mav;
	}

	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request) {

		service.memberRemove(member);

		HttpSession session = request.getSession();
		session.invalidate();

		return "/member/removeOk";

	}


}
