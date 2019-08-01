package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.SimpleBbsDto;

@Mapper
public interface ISimpleBbsDao {
	public List<SimpleBbsDto> listDao();	//����Ʈ�� ���� ��
	public SimpleBbsDto viewDao(String id);	//�Ѱ��� �� ������ ���� ��
	public int writeDao(String writer, String title, String content);	//�� ����
	public int deleteDao(String id);		//�� ����
}