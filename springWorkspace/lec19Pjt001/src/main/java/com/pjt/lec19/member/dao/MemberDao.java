package com.pjt.lec19.member.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pjt.lec19.member.Member;

@Repository
public class MemberDao implements IMemberDao{

	private HashMap<String, Member> dbMap;

	public MemberDao() {
		dbMap = new HashMap<String, Member>();
	}

	@Override
	public Map<String, Member> memberInsert(Member member) {
		System.out.println("=========== MemberDao.memberInsert() ===========");
		dbMap.put(member.getMemId(), member);

		return dbMap;
	}

	@Override
	public Member memberSelect(Member member) {
		System.out.println("=========== MemberDao.memberSelect() ===========");
		Member mem = dbMap.get(member.getMemId());
		return mem;
	}

	@Override
	public Member memberUpdate(Member member) {
		System.out.println("=========== MemberDao.memberUpdate() ===========");
		dbMap.put(member.getMemId(), member);
		return dbMap.get(member.getMemId());
	}

	@Override
	public Map<String, Member> memberDelete(Member member) {
		System.out.println("=========== MemberDao.memberDelete() ===========");
		dbMap.remove(member.getMemId());
		return dbMap;
	}

}
