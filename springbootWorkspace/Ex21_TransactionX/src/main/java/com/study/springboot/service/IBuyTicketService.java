package com.study.springboot.service;

public interface IBuyTicketService {
	public int buyTsX(String consumerId, int amount, String error);			//Transaction�� ������� �ʴ� ���
	public int buyTsManager(String consumerId, int amount, String error);	//Transaction Manager�� ����ϴ� ���
	public int buyTsTemplate(String consumerId, int amount, String error);	//Transaction Template�� ����ϴ� ���
}
