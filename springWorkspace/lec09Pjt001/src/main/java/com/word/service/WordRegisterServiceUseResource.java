package com.word.service;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordRegisterServiceUseResource {

	@Resource
	private WordDao wordDao;

	public WordRegisterServiceUseResource() {
	}

	//@Autowired
	public WordRegisterServiceUseResource(WordDao wordDao) {
		this.wordDao = wordDao;
	}

	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if (verify(wordKey)) {
			wordDao.insert(wordSet);
		} else {
			System.out.println("The word has already registered.");
		}
	}

	public boolean verify(String wordKey) {
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? true : false;
	}

	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
}
