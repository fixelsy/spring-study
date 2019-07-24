package com.study.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	//��Ʈ�ѷ��� ����ϱ� ���� annotation
public class MyController {
	@Autowired	//�ڵ�����
	Member member1;
	@Autowired	//�ڵ�����
	@Qualifier("printerB")	//printerA -> printerB
	Printer printer;
	@Autowired	//�ڵ�����
	Member member2;

	@RequestMapping("/")
	public @ResponseBody String root() {

		// 1.Member Bean ��������
		member1.print();

		// 2.PrinterB Bean ��������
		member1.setPrinter(printer);
		member1.print();

		//�̱������� Ȯ��
		if(member1 == member2) {
			System.out.println("������ ��ü�Դϴ�.");
		} else {
			System.out.println("���� �ٸ� ��ü�Դϴ�.");
		}
		return "Annotation ����ϱ�";
	}

}
