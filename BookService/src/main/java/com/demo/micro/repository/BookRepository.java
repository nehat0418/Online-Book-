package com.demo.micro.repository;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.micro.entity.Book;

public interface BookRepository extends MongoRepository<Book, String>{
	Optional<Book> findById(String id);
}
