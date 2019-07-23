package com.pjt.lec23.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.lec23.member.Member;
import com.pjt.lec23.member.dao.MemberDao;
import com.pjt.lec23.member.dao.MemberDao2;
import com.pjt.lec23.member.dao.MemberDao3;

@Service
public class MemberService implements IMemberService{

	//JDBC로 연결
//	@Autowired
//	MemberDao dao;

	//JdbcTemplate로 연결
//	@Autowired
//	MemberDao2 dao;

	//스프링 설정 파일을 이용해서 연결
	@Autowired
	MemberDao3 dao;

	@Override
	public void memberRegister(Member member) {
		int result = dao.memberInsert(member);

		if (result == 0) {
			System.out.println("Join Fail!!");
		} else {
			System.out.println("Join Success!!");
		}

	}

	@Override
	public Member memberSearch(Member member) {
		Member mem = dao.memberSelect(member);

		if (mem == null) {
			System.out.println("Login Fail!!");
		} else {
			System.out.println("Login Success!!");
		}
		return mem;
	}

	@Override
	public Member memberModify(Member member) {
		int result = dao.memberUpdate(member);

		if ( result == 0) {
			System.out.println("Modify Fail!!");
			return null;
		} else {
			System.out.println("Modify Success!!");
		}

		return member;
	}

	@Override
	public int memberRemove(Member member) {
		int result = dao.memberDelete(member);

		if(result == 0) {
			System.out.println("Remove Fail!!!");
		} else {
			System.out.println("Remove Success!!");
		}

		return result;
	}

}
