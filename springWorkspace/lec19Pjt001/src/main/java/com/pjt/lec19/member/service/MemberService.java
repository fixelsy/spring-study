package com.pjt.lec19.member.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.lec19.member.MemPhone;
import com.pjt.lec19.member.Member;
import com.pjt.lec19.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService{

	@Autowired
	MemberDao dao;

	@Override
	public void memberRegister(Member member) {
		System.out.println("======== MemberService.memberRegister() ========");
		printMembers(dao.memberInsert(member));
	}

	@Override
	public void memberSearch(Member member) {
		System.out.println("======== MemberService.memberSearch() ========");
		printMember(dao.memberSelect(member));
	}

	@Override
	public Member[] memberModify(Member member) {
		System.out.println("======== MemberService.memberModify() ========");

		Member memBef = dao.memberSelect(member);
		Member memAft = dao.memberUpdate(member);
		printMember(memAft);

		return new Member[] {memBef, memAft};
	}

	@Override
	public void memberRemove(Member member) {
		System.out.println("======== MemberService.memberRemove() ========");

		printMembers(dao.memberDelete(member));
	}

	private void printMembers(Map<String, Member> map) {

		Set<String> keys = map.keySet();
		Iterator<String> iterator = keys.iterator();

		while(iterator.hasNext()) {
			String key = iterator.next();
			Member mem = map.get(key);
			printMember(mem);
		}
	}

	private void printMember(Member mem) {
		System.out.print("ID." + mem.getMemId() + "\t");
		System.out.print("|PW." + mem.getMemPw() + "\t");
		System.out.print("|MAIL." + mem.getMemMail() + "\t");

		List<MemPhone> memPhones = mem.getMemPhones();
		for (MemPhone memPhone : memPhones) {
			System.out.print("|PHONE." + memPhone.getMemPhone1() + "-" + memPhone.getMemPhone2() + "-" + memPhone.getMemPhone3() + "\t");
		}

		System.out.print("|AGE." + mem.getMemAge() + "\t");
		System.out.print("|ADULT." + mem.isMemAdult() + "\t");
		System.out.print("|GENDER." + mem.getMemGender() + "\t");
		System.out.print("|FAVORITE SPORTS." + Arrays.toString(mem.getMemFSports()) + "\n");
	}
}
