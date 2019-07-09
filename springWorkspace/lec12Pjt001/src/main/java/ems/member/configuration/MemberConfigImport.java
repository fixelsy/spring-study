package ems.member.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ems.member.dao.StudentDao;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentDeleteService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

//클래스파일이 xml을 대신해서 스프링컨테이너를 생성할 수 있는 annotation
@Configuration
//스프링 컨테이너에 사용되는 class파일을 import하는 annotation
@Import({MemberConfig1.class, MemberConfig2.class, MemberConfig3.class})
public class MemberConfigImport {

}
