package com.study.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	//컨트롤러로 사용하기 위한 annotation
public class MyController {
	@Autowired	//자동주입
	Member member1;
	@Autowired	//자동주입
	@Qualifier("printerB")	//printerA -> printerB
	Printer printer;
	@Autowired	//자동주입
	Member member2;

	@RequestMapping("/")
	public @ResponseBody String root() {

		// 1.Member Bean 가져오기
		member1.print();

		// 2.PrinterB Bean 가져오기
		member1.setPrinter(printer);
		member1.print();

		//싱글톤인지 확인
		if(member1 == member2) {
			System.out.println("동일한 객체입니다.");
		} else {
			System.out.println("서로 다른 객체입니다.");
		}
		return "Annotation 사용하기";
	}

}
