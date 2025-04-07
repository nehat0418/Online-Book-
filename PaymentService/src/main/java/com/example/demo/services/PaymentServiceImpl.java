package com.example.demo.services;

import com.example.demo.entities.Payment;
import com.example.demo.repository.PaymentRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	 private final PaymentRepository paymentRepository;

	    public PaymentServiceImpl(PaymentRepository paymentRepository) {
	        this.paymentRepository = paymentRepository;
	    }
    @Override
    public boolean initiatePaymentTransaction(Payment payment) {
        paymentRepository.save(payment);
        return true;
    }

    @Override
    public boolean validatePaymentInformation(Payment payment) {
        // Validate CVV
        String cvv = payment.getCVV();
        if (cvv == null || cvv.length() != 3) {
            return false; // CVV must be 3 digits
        }

        // Validate card number
        String cardNumber = payment.getCardNumber();
        if (cardNumber == null || cardNumber.length() != 16) {
            return false; // Card number must be 16 digits
        }

        // Validate other conditions
        if (payment.getAmount() == 0 || cvv.equals("") || cardNumber.equals("")) {
            return false;
        }

        return true;
    }


	@Override
	public Payment getPaymentById(String paymentId) {
	    return paymentRepository.findById(paymentId).orElse(null);
	}
	@Override
	public boolean validatePayment(Payment payment) {
		
		return false;
	}
	@Override
	public List<Payment> findPaymentsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}