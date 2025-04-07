package com.example.demo;

import com.demo.micro.entity.Book;
import com.demo.micro.repository.BookRepository;
import com.demo.micro.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void testCreate_ValidBook() {
        Book book = createValidBook();

        Book createdBook = bookService.create(book);

        assertNotNull(createdBook);
        assertEquals(book.getBookName(), createdBook.getBookName());
        assertEquals(book.getAuthor(), createdBook.getAuthor());
        
    }

    @Test
    public void testCreate_InvalidBook() {
        Book book = createInvalidBook();

        Book createdBook = bookService.create(book);

        assertNull(createdBook);
    }

   

    @Test
    public void testGetBookById_ExistingBook() {
        String bookId = "book123";
        Book book = createValidBook();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book fetchedBook = bookService.getBookById(bookId);

        assertNotNull(fetchedBook);
        assertEquals(bookId, fetchedBook.getId());
    }

    @Test
    public void testGetBookById_NonExistingBook() {
        String bookId = "nonExistingBookId";
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        Book fetchedBook = bookService.getBookById(bookId);

        assertNull(fetchedBook);
    }

    //  creating sample books for testing
    private Book createValidBook() {
        return new Book("Test Book", "Test Author", "Country", "Language", "Genre",
                "Publisher", 300, "Feedback", "http://example.com/book.jpg", 25);
    }

    private Book createInvalidBook() {
        // Create a book with some invalid fields 
        return new Book("", "", "", "", "",
                "", 0, "", "", 0);
    }
    
    
    
    
   

   
    
}
