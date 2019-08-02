package com.study.springboot.service;

public interface IBuyTicketService {
	public int buyTsX(String consumerId, int amount, String error);			//Transaction을 사용하지 않는 경우
	public int buyTsManager(String consumerId, int amount, String error);	//Transaction Manager를 사용하는 경우
	public int buyTsTemplate(String consumerId, int amount, String error);	//Transaction Template를 사용하는 경우
}
