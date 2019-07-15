package com.pjt.lec19.member.service;

import com.pjt.lec19.member.Member;

public interface IMemberService {
	void memberRegister(Member member);
	void memberSearch(Member member);
	Member[] memberModify(Member member);
	void memberRemove(Member member);
}
