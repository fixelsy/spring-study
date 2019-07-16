package com.pjt.lec20.member.service;

import com.pjt.lec20.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
	Member memberModify(Member member);
	void memberRemove(Member member);
}
