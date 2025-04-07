package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.Cart;



public interface CartRepository extends MongoRepository<Cart, String>{

	Cart findByUserId(String userId);
}
