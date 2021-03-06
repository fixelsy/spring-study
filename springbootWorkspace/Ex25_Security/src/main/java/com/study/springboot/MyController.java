package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Security";
	}

	@RequestMapping("/guest/welcome")
	public String welcome1() {
		return "guest/welcome1";
	}

	@RequestMapping("/member/welcome")
	public String welcome2() {
		return "member/welcome2";
	}

	@RequestMapping("/admin/welcome")
	public String welcome3() {
		return "admin/welcome3";
	}


	/**
	 * Custom Login 페이지 만들어서 사용하기
	 */
	@RequestMapping("/customLoginForm")
	public String customLoginForm() {
		return "securityCustom/customLoginForm";
	}

	/**
	 * Custom Login 페이지 만들어서 사용하기
	 */
	@RequestMapping("/customLoginError")
	public String customLoginError() {
		return "securityCustom/customLoginError";
	}


	@RequestMapping("/sChkLoginForm")
	public String sChkLoginForm() {
		return "securityStatusCheck/loginForm";
	}

}
