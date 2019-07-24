package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.springboot.bean.Config;
import com.study.springboot.bean.Member;
import com.study.springboot.bean.Printer;

//@SpringBootApplication
public class Ex02JavaCodeDiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Ex02JavaCodeDiApplication.class, args);

		// 1.IoC �����̳� ����
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		// 2-1.Member Bean ��������(Bean�� �����ͼ� ����ȯ)
		Member member1 = (Member)context.getBean("member1");
		member1.print();

		// 2-2.Member Bean ��������(Ŭ���� ������)
		Member member2 = context.getBean("hello", Member.class);
		member2.print();

		// 2-3.PrinterB Bean ��������(Bean�� �����ͼ� ���� ����)
		Printer printer = context.getBean("printerB", Printer.class);
		member1.setPrinter(printer);
		member1.print();

		// 3.���� �̱������� Ȯ��
		if (member1 == member2) {
			System.out.println("������ ��ü�Դϴ�.");
		} else {
			System.out.println("���� �ٸ� ��ü�Դϴ�.");
		}
	}

}