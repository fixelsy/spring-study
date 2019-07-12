package com.pjt.lec18.member;

public class Member {
	private String memId;
	private String memPw;
	private String memMail;
	private MemPhone memPhone;

	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public MemPhone getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(MemPhone memPhone) {
		this.memPhone = memPhone;
	}

	@Override
	public String toString() {
		return "memberId : " + memId + "\t| memberPw :" + memPw + "\t| memberMail : " + memMail + "\t| memberPhone : " + memPhone.toString();
	}

}
