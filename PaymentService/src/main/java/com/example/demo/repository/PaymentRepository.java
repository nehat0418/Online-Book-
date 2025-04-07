package com.example.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String>{
	List<Payment> findPaymentsByUserId(String userId);
}
