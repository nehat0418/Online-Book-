package com.demo.micro.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Document(collection="user_books")
public class Book {

	@Id
	private String id;
	private String bookName;
	private String author;
	private String country;
	private String language;
    private String genre;
    private String publisher;
    private int pages;
    private int  price;
    private String imageUrl;
    private String feedback;
	
	public String getId() {
		return id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Book(String bookName, String author, String country, String language, String genre,
			String publisher, int pages, String feedback, String imageUrl,  int price) {
		super();
	
		this.bookName = bookName;
		this.author = author;
		this.country = country;
		this.language = language;
		this.genre = genre;
		this.publisher = publisher;
		this.pages = pages;
		this.price = price;
		this.imageUrl = imageUrl;
		this.feedback = feedback;
	}
	
	
	
	
	
	
	
	
    
}