package com.study.springboot.service;

public interface IBuyTicketService {
	public int buyTsX(String consumerId, int amount, String error);
	public int buyTsManager(String consumerId, int amount, String error);
}
