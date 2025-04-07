package com.demo.micro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.micro.entity.Book;
import com.demo.micro.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookrepository;
	
	@Override
	public Book create(Book book) {
	    if (isInvalidBook(book)) {
	        return null;
	    }
	    return bookrepository.save(book);
	}


	private boolean isInvalidBook(Book book) {
	    return  book.getBookName().isEmpty()
	            || book.getAuthor().isEmpty() || book.getCountry().isEmpty()
	            || book.getLanguage().isEmpty() || book.getGenre().isEmpty()
	            || book.getPublisher().isEmpty() || book.getPages() <= 0
	            || book.getPrice() <= 0 || book.getImageUrl().isEmpty()
	            || book.getFeedback().isEmpty();
	}


	@Override
	public List<Book> getBooks() {
		return bookrepository.findAll();
	}

	@Override
	public Object addBookToCart(Book book) {
		return null;
	}
	
	@Override
	public Object update(Book book) {
		return bookrepository.save(book);
	}

	
	@Override
	public Book getBookById(String bookId) {
		Optional<Book> book = bookrepository.findById(bookId);
		if(book.isPresent()) {
			return book.get();
		} else {
			return null;
		}
	}
	@Override
	public List<Book> getBooksById(String Id) {
		return null;
	}

}


	


