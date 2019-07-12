package com.pjt.lec18.member.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.pjt.lec18.member.MemPhone;
import com.pjt.lec18.member.Member;

@Repository
public class MemberDao implements IMemberDao {

	private HashMap<String, Member> dbMap;

	public MemberDao() {
		dbMap = new HashMap<String, Member>();
	}

	@Override
	public void memberInsert(String memId, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3) {
		System.out.println("==============MemberDao.memberInsert()==============");
		System.out.println("memId : " + memId + "\t| memPw :" + memPw + "\t| memMail : " + memMail + "\t| memPhone : " + memPhone1 + "-" + memPhone2 + "-" + memPhone3);

		Member member = new Member();
		member.setMemId(memId);
		member.setMemPw(memPw);
		member.setMemMail(memMail);
		MemPhone memPhone = new MemPhone();
		memPhone.setMemPhone1(memPhone1);
		memPhone.setMemPhone2(memPhone2);
		memPhone.setMemPhone3(memPhone3);
		member.setMemPhone(memPhone);

		dbMap.put(memId, member);

		Set<String> keys = dbMap.keySet();
		Iterator<String> iterator = keys.iterator();

		while(iterator.hasNext()) {
			String key = iterator.next();
			Member mem = dbMap.get(key);
			System.out.println(mem.toString());
		}
	}


	@Override
	public void memberInsert(Member member) {
		System.out.println("===========MemberDao.memberInsert(Member)===========");
		System.out.println(member.toString());

		dbMap.put(member.getMemId(), member);

		Set<String> keys = dbMap.keySet();
		Iterator<String> iterator = keys.iterator();

		while(iterator.hasNext()) {
			String key = iterator.next();
			Member mem = dbMap.get(key);
			System.out.println(mem.toString());
		}


	}

	@Override
	public Member memberSelect(String memId, String memPw) {
		System.out.println("==============MemberDao.memberSelect()==============");
		System.out.println("memId : " + memId + "\t| memPw :" + memPw);
		Member member = dbMap.get(memId);

		return member;
	}

	@Override
	public void memberUpdate() {

	}

	@Override
	public void memberDelete() {

	}


}
