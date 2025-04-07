package com.example.demo.services;



import java.util.List;

import com.example.demo.entities.Payment;

public interface PaymentService {
    boolean initiatePaymentTransaction(Payment payment);
    boolean validatePaymentInformation(Payment payment);
	Payment getPaymentById(String paymentId);
	boolean validatePayment(Payment payment);
	List<Payment> findPaymentsByUserId(String userId);
	
}

