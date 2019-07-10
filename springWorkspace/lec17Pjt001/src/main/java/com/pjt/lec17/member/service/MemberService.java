package com.pjt.lec17.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pjt.lec17.member.Member;
import com.pjt.lec17.member.dao.MemberDao;

// 서비스 객체 구현 3-1)annotation을 이용해서 서비스 객체 생성 및 의존 객체 자동 주입
@Service
@Component
@Repository("memService")
public class MemberService implements IMemberService{

	@Autowired
	MemberDao dao;

	@Override
	public void memberRegister(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3) {
		System.out.println("===================== MemberService.memberRegister() =====================");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw);
		System.out.println("memMail : " + memMail);
		System.out.println("memPhone : " + memPhone1 + " - " + memPhone2 + " - " + memPhone3);

		dao.memberInsert(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);

	}

	@Override
	public Member memberSearch(String memId, String memPw) {
		System.out.println("===================== MemberService.memberSearch() =====================");
		System.out.println("memId : " + memId);
		System.out.println("memPw : " + memPw);

		Member member = dao.memberSelect(memId, memPw);		return member;
	}

	@Override
	public void memberModify() {

	}

	@Override
	public void memberRemove() {

	}

}
