package ems.member.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.DataBaseConnectionInfo;

//클래스파일이 xml을 대신해서 스프링컨테이너를 생성할 수 있는 annotation
@Configuration
public class MemberConfig2 {

	/*
	*	<bean id="dataBaseConnectionInfoDev" class="ems.member.DataBaseConnectionInfo">
	*	<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe"></property>
	*	<property name="userId" value="scott"></property>
	*	<property name="userPw" value="tiger"></property>
	*	</bean>
	*/
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
		infoDev.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		infoDev.setUserId("scott");
		infoDev.setUserPw("tiger");
		return infoDev;
	}

	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo infoReal = new DataBaseConnectionInfo();
		infoReal.setJdbcUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
		infoReal.setUserId("masterid");
		infoReal.setUserPw("masterpw");
		return infoReal;
	}
}
