package com.brms.book.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.brms.book.Book;
import com.brms.book.dao.BookDao;

public class BookRegisterService {

	@Autowired
	private BookDao bookDao;

	public BookRegisterService() {
	}

	public void register(Book book) {
		bookDao.insert(book);
	}

}
