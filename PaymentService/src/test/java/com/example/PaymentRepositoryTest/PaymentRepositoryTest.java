package com.example.PaymentRepositoryTest;


import com.example.demo.entities.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.services.PaymentService;
import com.example.demo.services.PaymentServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;



import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

 // Test method to test the getPaymentById functionality
    @Test
    public void testGetPaymentById() {
        // Arrange
        String paymentId = "paymentId";
        // Creating a sample Payment object
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "123", "12/23", "Completed");
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

        // Calling the getPaymentById method of PaymentService
        Optional<Payment> result = Optional.of(paymentService.getPaymentById(paymentId));

     // Checking if the returned Payment object matches the expected Payment object
        assertEquals(payment, result.orElse(null));
    }
}
