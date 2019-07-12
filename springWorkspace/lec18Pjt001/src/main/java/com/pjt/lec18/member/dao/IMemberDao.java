package com.pjt.lec18.member.dao;

import com.pjt.lec18.member.Member;

public interface IMemberDao {
	void memberInsert(String memID, String memPw, String memMail, String memPhone1, String memPhone2, String memPhone3);
	void memberInsert(Member member);
	Member memberSelect(String memId, String memPw);
	void memberUpdate();
	void memberDelete();
}
