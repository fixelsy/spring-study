package ems.member.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.dao.StudentDao;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentDeleteService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

//클래스파일이 xml을 대신해서 스프링컨테이너를 생성할 수 있는 annotation
@Configuration
public class MemberConfig1 {

	//<bean id="studentDao" class="ems.member.dao.StudentDao"></bean>
	//public '반환할 타입' 'id값'() { };
	//bean객체로 만드는 annotation
	@Bean
	public StudentDao studentDao() {
		return new StudentDao();
	}

	/*
	* 	<bean id="allSelectService" class="ems.member.service.StudentAllSelectService">
	*	<constructor-arg ref="studentDao"></constructor-arg>
	*	</bean>
	*/
	@Bean
	public StudentAllSelectService allSelectService() {
		return new StudentAllSelectService(studentDao());
	}

	@Bean
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}

	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}

	@Bean
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}

	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}

}
