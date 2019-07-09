package ems.member.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.DataBaseConnectionInfo;
import ems.member.dao.StudentDao;
import ems.member.service.EMSInformationService;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentDeleteService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

//클래스파일이 xml을 대신해서 스프링컨테이너를 생성할 수 있는 annotation
@Configuration
public class MemberConfig {

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

	@Bean
	public EMSInformationService informationService() {
		EMSInformationService EMSinfo = new EMSInformationService();
		EMSinfo.setInfo("Education Management System program was developed in 2015.");
		EMSinfo.setCopyRight("COPYRIGHT(C) 2015 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MAS");
		EMSinfo.setVer("The version is 1.0");
		EMSinfo.setsYear(2015);
		EMSinfo.setsMonth(1);
		EMSinfo.setsDay(1);
		EMSinfo.seteYear(2015);
		EMSinfo.seteMonth(2);
		EMSinfo.seteDay(28);

		/*
		*	<property name="developers">
		*		<list>
		*			<value>Soyeon.</value>
		*			<value>Eloy.</value>
		*			<value>Jasper.</value>
		*			<value>Dillon.</value>
		*			<value>Kian.</value>
		*		</list>
		*	</property>
		*/
		List<String> developers = new ArrayList<String>();
		developers.add("Soyeon.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		EMSinfo.setDevelopers(developers);


		/*
		*	<property name="administrators">
		*		<map>
		*			<entry>
		*				<key><value>Soyeon</value></key>
		*				<value>soyeon@springPjt.org</value>
		*			</entry>
		*			<entry>
		*				<key><value>Jasper</value></key>
		*				<value>jasper@springPjt.org</value>
		*			</entry>
		*		</map>
		*	</property>
		*/
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Soyeon", "soyeon@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		EMSinfo.setAdministrators(administrators);


		/*
		* 	<property name="dbInfos">
		*		<map>
		*			<entry>
		*				<key><value>dev</value></key>
		*				<ref bean="dataBaseConnectionInfoDev" />
		*			</entry>
		*			<entry>
		*				<key><value>real</value></key>
		*				<ref bean="dataBaseConnectionInfoReal" />
		*			</entry>
		*		</map>
		*	</property>
		*/
		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		dbInfos.put("dev", dataBaseConnectionInfoDev());
		dbInfos.put("real", dataBaseConnectionInfoReal());
		EMSinfo.setDbInfos(dbInfos);

		return EMSinfo;
	}
}
