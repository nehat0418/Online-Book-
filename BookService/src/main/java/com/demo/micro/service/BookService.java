package com.demo.micro.service;

import java.util.List;

import com.demo.micro.entity.Book;

public interface BookService {

	List<Book> getBooks();
	List<Book> getBooksById(String Id);
	Object addBookToCart(Book book);
	Book getBookById(String bookId);
	Object update(Book book);
	Book create(Book book);
}
