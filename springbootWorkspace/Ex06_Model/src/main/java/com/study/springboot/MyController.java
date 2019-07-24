package com.study.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception {
		return "Model & View";
	}

	@RequestMapping("/test1")
	public String test1(Model model) {
		//Model 객체 : view로 Data 전달
		model.addAttribute("name", "홍길동");
		return "test1";
	}

	@RequestMapping("/mav")
	public ModelAndView test2() {
		//ModelAndView 객체 : view로 view의 이름과 Data 동시에 전달
		ModelAndView mav = new ModelAndView();

		List<String> list = new ArrayList<>();

		list.add("test1");
		list.add("test2");
		list.add("test3");

		mav.addObject("lists", list);				//jstl로 호출
		mav.addObject("ObjectTest", "테스트입니다.");	//jstl로 호출
		mav.addObject("name", "홍길동");				//jstl로 호출
		mav.setViewName("view/myView");				//jstl로 호출

		return mav;
	}



}
