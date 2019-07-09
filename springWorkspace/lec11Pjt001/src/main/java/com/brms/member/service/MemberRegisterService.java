package com.brms.member.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.brms.member.Member;
import com.brms.member.dao.MemberDao;

public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService() {
	}

	public void register(Member member) {
		memberDao.insert(member);
	}

	public void initMethod() {
		System.out.println("init-method ==> MemberRegisterService bean 객체 생성");
	}

	public void destroyMethod() {
		System.out.println("destroy-method ==> MemberRegisterService bean 객체 소멸");
	}
}
