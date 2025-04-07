package com.example.demo;

import com.demo.micro.entity.Book;
import com.demo.micro.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testSaveBook() {
        Book book = new Book("Test Book", "Test Author", "Country", "Language", "Genre",
                "Publisher", 300, "Feedback", "http://example.com/book.jpg", 25);

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
    }

    @Test
    public void testFindById_ExistingBook() {
        Book book = new Book("Test Book", "Test Author", "Country", "Language", "Genre",
                "Publisher", 300, "Feedback", "http://example.com/book.jpg", 25);

        Book savedBook = bookRepository.save(book);
        String bookId = savedBook.getId();

        Optional<Book> fetchedBookOptional = bookRepository.findById(bookId);

        assertTrue(fetchedBookOptional.isPresent());
        Book fetchedBook = fetchedBookOptional.get();
        assertEquals(bookId, fetchedBook.getId());
    }

    @Test
    public void testFindById_NonExistingBook() {
        Optional<Book> fetchedBookOptional = bookRepository.findById("nonExistingBookId");

        assertFalse(fetchedBookOptional.isPresent());
    }

    
}
