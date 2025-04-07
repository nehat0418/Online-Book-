package com.example.demo;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.micro.controller.BookController;
import com.demo.micro.entity.Book;
import com.demo.micro.service.BookService;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateBookSuccessfully() {
        // Mock the bookService.create() method to return a book when called
        Book book = new Book("BookId", "BookName", "Author", "Country", "Language", "Genre", 200, "Feedback", "ImageUrl", 100);
        when(bookService.create(any(Book.class))).thenReturn(book);

        // Call the create endpoint in the controller
        ResponseEntity<Object> response = bookController.create(book);

        // Assert that the response is successful and contains the created book
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == book;
    }

    @Test
    public void shouldFailToCreateBook_InvalidInput() {
        // Create a book with empty values, which should result in failure
        Book invalidBook = new Book("", "", "", "", "", "", 0, "", "", 0);

        // Call the create endpoint in the controller with the invalid book
        ResponseEntity<Object> response = bookController.create(invalidBook);

        // Assert that the response is a bad request with an error message
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert response.getBody().equals("Fill all the requirements.");
    }

    @Test
    public void shouldGetListOfBooks() {
        // Mock the bookService.getBooks() method to return a list of books when called
        List<Book> books = Arrays.asList(
            new Book("Book1", "Author1", "Country1", "Language1", "Genre1", "Publisher1", 100, "Feedback1", "ImageUrl1", 50),
            new Book("Book2", "Author2", "Country2", "Language2", "Genre2", "Publisher2", 150, "Feedback2", "ImageUrl2", 75)
        );
        when(bookService.getBooks()).thenReturn(books);

        // Call the getBooks endpoint in the controller
        ResponseEntity<List<Book>> response = bookController.getBooks();

        // Assert that the response is successful and contains the list of books
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == books;
    }

    @Test
    public void shouldGetBookByIdSuccessfully() {
        // Mock the bookService.getBookById() method to return a book when called with any bookId
        Book book = new Book("BookId", "BookName", "Author", "Country", "Language", "Genre", 200, "Feedback", "ImageUrl", 100);
        when(bookService.getBookById(anyString())).thenReturn(book);

        // Call the getBookById endpoint in the controller with a valid bookId
        ResponseEntity<Object> response = bookController.getBookByUserId("BookId");

        // Assert that the response is successful and contains the retrieved book
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == book;
    }

    @Test
    public void shouldFailToGetBookById_InvalidBookId() {
        // Mock the bookService.getBookById() method to return null when called with any bookId
        when(bookService.getBookById(anyString())).thenReturn(null);

        // Call the getBookById endpoint in the controller with an invalid bookId
        ResponseEntity<Object> response = bookController.getBookByUserId("InvalidBookId");

        // Assert that the response is not found (book with given ID not found)
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }

    @Test
    public void shouldUpdateBookSuccessfully() {
        // Mock the bookService.getBookById() method to return an existing book when called
        Book existingBook = new Book("BookId", "BookName", "Author", "Country", "Language", "Genre", 200, "Feedback", "ImageUrl", 100);
        when(bookService.getBookById(anyString())).thenReturn(existingBook);

        // Mock the bookService.update() method to return the updated book when called
        Book updatedBook = new Book("BookId", "UpdatedBookName", "UpdatedAuthor", "UpdatedCountry", "UpdatedLanguage", "UpdatedGenre", 250, "UpdatedFeedback", "UpdatedImageUrl", 120);
        when(bookService.update(any(Book.class))).thenReturn(updatedBook);

        // Call the updateBookByUserId endpoint in the controller with an updated book object
        Book updatedBookRequest = new Book("UpdatedBookName", "UpdatedAuthor", "UpdatedCountry", "UpdatedLanguage", "UpdatedGenre", "UpdatedPublisher", 250, "UpdatedFeedback", "UpdatedImageUrl", 120);
        ResponseEntity<Object> response = bookController.updateBookByUserId("BookId", updatedBookRequest);

        // Assert that the response is successful and contains the updated book
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == updatedBook;
    }

    @Test
    public void shouldFailToUpdateBook_BookNotFound() {
        // Mock the bookService.getBookById() method to return null when called with any bookId
        when(bookService.getBookById(anyString())).thenReturn(null);

        // Call the updateBookByUserId endpoint in the controller with an updated book object and an invalid bookId
        Book updatedBookRequest = new Book("UpdatedBookName", "UpdatedAuthor", "UpdatedCountry", "UpdatedLanguage", "UpdatedGenre", "UpdatedPublisher", 250, "UpdatedFeedback", "UpdatedImageUrl", 120);
        ResponseEntity<Object> response = bookController.updateBookByUserId("InvalidBookId", updatedBookRequest);

        // Assert that the response is not found (book with given ID not found)
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }
}
