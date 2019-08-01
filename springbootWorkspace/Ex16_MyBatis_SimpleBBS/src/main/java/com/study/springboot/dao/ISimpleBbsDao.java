package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.SimpleBbsDto;

@Mapper
public interface ISimpleBbsDao {
	public List<SimpleBbsDto> listDao();	//리스트를 보는 것
	public SimpleBbsDto viewDao(String id);	//한개의 글 내용을 보는 것
	public int writeDao(String writer, String title, String content);	//글 쓰기
	public int deleteDao(String id);		//글 삭제
}
