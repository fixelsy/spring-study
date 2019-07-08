package com.word.dao;

import java.util.HashMap;
import java.util.Map;

import com.word.WordSet;

public class WordDao {

	private Map<String, WordSet> wordDB = new HashMap<String, WordSet>();

	public WordSet select(String wordKey) {
		return wordDB.get(wordKey);
	}

	public void insert(WordSet wordSet) {
		wordDB.put(wordSet.getWordKey(), wordSet);
	}

	public void update(WordSet wordSet) {
		wordDB.put(wordSet.getWordKey(), wordSet);
	}

	public void delete(String sNum) {
		wordDB.remove(sNum);
	}

	public Map<String, WordSet> getWordDB() {
		return wordDB;
	}

}
