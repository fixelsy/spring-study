package com.pjt.lec16;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {

		System.out.println("========== home() method ==========");
		model.addAttribute("key", "home's value");

		return "home";	//home.jsp
	}


	@RequestMapping("/login")
	public String login(Model model) {

		System.out.println("========== login() method ==========");
		model.addAttribute("key", "login's value");

		return "login";	//login.jsp
	}
}
