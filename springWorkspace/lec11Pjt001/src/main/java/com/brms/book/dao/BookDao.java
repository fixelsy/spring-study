package com.brms.book.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;

import com.brms.book.Book;

public class BookDao {

	private Map<String, Book> bookDB = new HashMap<String, Book>();

	public Book select(String bNum) {
		return bookDB.get(bNum);
	}

	public void insert(Book book) {
		bookDB.put(book.getbNum(), book);
	}

	public void update(Book book) {

	}

	public void delete() {

	}

	public Map<String, Book> getBookDB() {
		return bookDB;
	}
}
