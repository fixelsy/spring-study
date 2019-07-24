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
		//Model ��ü : view�� Data ����
		model.addAttribute("name", "ȫ�浿");
		return "test1";
	}

	@RequestMapping("/mav")
	public ModelAndView test2() {
		//ModelAndView ��ü : view�� view�� �̸��� Data ���ÿ� ����
		ModelAndView mav = new ModelAndView();

		List<String> list = new ArrayList<>();

		list.add("test1");
		list.add("test2");
		list.add("test3");

		mav.addObject("lists", list);				//jstl�� ȣ��
		mav.addObject("ObjectTest", "�׽�Ʈ�Դϴ�.");	//jstl�� ȣ��
		mav.addObject("name", "ȫ�浿");				//jstl�� ȣ��
		mav.setViewName("view/myView");				//jstl�� ȣ��

		return mav;
	}



}
