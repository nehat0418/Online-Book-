package com.demo.micro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.demo.micro.entity.Book;
import com.demo.micro.service.BookService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins="http://localhost:4200")
public class BookController {
	
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody Book book) {
		
		//validation for empty values
	    if ( book.getBookName().equals("") || book.getAuthor().equals("")
	            || book.getCountry().equals("") || book.getLanguage().equals("") || book.getGenre().equals("")
	            || book.getPublisher().equals("") || book.getPages() <= 0 || book.getPrice() <= 0
	            || book.getImageUrl().equals("") || book.getFeedback().equals("")) {
	        return ResponseEntity.badRequest().body("Fill all the requirements.");
	    }
	    
	    // If all the validations pass, call the service to create the book.
	    Book createdBook = bookService.create(book);
	    if (createdBook != null) {
	        return ResponseEntity.ok(createdBook);
	    }
	    else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the book.");
	    }
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getBooks(){
		return ResponseEntity.ok(bookService.getBooks());
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<Object> getBookByUserId(@PathVariable String bookId) {
		
		//validate if bookId is empty
	    if (bookId.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    Book book = bookService.getBookById(bookId);
	    System.out.print(book);
        return ResponseEntity.ok(book);
    }

	@PutMapping("/{bookId}")
	public ResponseEntity<Object> updateBookByUserId(@PathVariable String bookId, @RequestBody Book updatedBook) {
		
	    Book book = bookService.getBookById(bookId);
	    
	    //validate if book is null
	    if (book == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    book.setBookName(updatedBook.getBookName());
	    book.setAuthor(updatedBook.getAuthor());
	    book.setCountry(updatedBook.getCountry());
	    book.setLanguage(updatedBook.getLanguage());
	    book.setGenre(updatedBook.getGenre());
	    book.setPublisher(updatedBook.getPublisher());
	    book.setPages(updatedBook.getPages());
	    book.setImageUrl(updatedBook.getImageUrl());
	    book.setFeedback(updatedBook.getFeedback());
	
	    Object updatedBookObj = bookService.update(book);
	    return ResponseEntity.ok(updatedBookObj);
	}
}
