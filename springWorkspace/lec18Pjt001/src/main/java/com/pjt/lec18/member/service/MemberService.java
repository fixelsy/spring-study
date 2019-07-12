package com.pjt.lec18.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjt.lec18.member.Member;
import com.pjt.lec18.member.dao.MemberDao;

//@Service
//@Service("memService")
//@Component
//@Component("memService")
//@Repository
@Repository("memService")
public class MemberService implements IMemberService {

	@Autowired
	MemberDao dao;

	@Override
	public void memberRegister(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3) {
		System.out.println("==============MemberService.memberRegister()==============");
		System.out.println("memId : " + memId + "\t| memPw :" + memPw + "\t| memMail : " + memMail + "\t| memPhone : " + memPhone1 + "-" + memPhone2 + "-" + memPhone3);

		dao.memberInsert(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
	}


	@Override
	public void memberRegister(Member member) {
		System.out.println("===========MemberService.memberRegister(Member)===========");
		System.out.println("memId : " + member.getMemId() + "\t| memPw :" + member.getMemPw() +
				"\t| memMail : " + member.getMemMail() + "\t| memPhone : " +
				member.getMemPhone().getMemPhone1() + "-" +
				member.getMemPhone().getMemPhone2() + "-" +
				member.getMemPhone().getMemPhone3());

		dao.memberInsert(member);
	}

	@Override
	public Member memberSearch(String memId, String memPw) {
		System.out.println("==============MemberService.memberSearch()==============");
		System.out.println("memId : " + memId + "\t| memPw :" + memPw);

		Member member = dao.memberSelect(memId, memPw);
		return member;
	}

	@Override
	public void memberModify() {

	}

	@Override
	public void memberRemove() {

	}


}
