package com.demo.micro.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.micro.entities.User;

public interface UserRepository extends MongoRepository<User, String>{



	User findByName(String name);

	User findByEmail(String email);
	
}
