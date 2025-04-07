package com.example.PaymentServiceImplTest;

import com.example.demo.entities.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.services.PaymentServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    public PaymentServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitiatePaymentTransaction() {
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "123", "12/23", "Completed");

        // Mock the save method of the paymentRepository
        when(paymentRepository.save(payment)).thenReturn(payment);

        // Call the initiatePaymentTransaction method
        boolean result = paymentService.initiatePaymentTransaction(payment);

        // Verify that the save method was called
        verify(paymentRepository, times(1)).save(payment);

        // Assert that the result is true
        assertTrue(result);
    }

    @Test
    void testValidatePaymentInformation_ValidPayment() {
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "123", "12/23", "Completed");

        // Call the validatePaymentInformation method
        boolean result = paymentService.validatePaymentInformation(payment);

        // Assert that the result is true
        assertTrue(result);
    }

    @Test
    void testValidatePaymentInformation_InvalidCVV() {
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "12", "12/23", "Completed");

        // Call the validatePaymentInformation method
        boolean result = paymentService.validatePaymentInformation(payment);

        // Assert that the result is false
        assertFalse(result);
    }

    @Test
    void testValidatePaymentInformation_InvalidCardNumber() {
        Payment payment = new Payment("userId", 100.0, "123", "123", "12/23", "Completed");
        boolean result = paymentService.validatePaymentInformation(payment);  
        assertFalse(result);
    }

    @Test
    void testValidatePaymentInformation_ZeroAmount() {
        Payment payment = new Payment("userId", 0, "1234567890123456", "123", "12/23", "Completed");

        // Call the validatePaymentInformation method
        boolean result = paymentService.validatePaymentInformation(payment);

        // Assert that the result is false
        assertFalse(result);
    }

    @Test
    void testValidatePaymentInformation_NullCVV() {
        Payment payment = new Payment("userId", 100.0, "1234567890123456", null, "12/23", "Completed");

        // Call the validatePaymentInformation method
        boolean result = paymentService.validatePaymentInformation(payment);

        // Assert that the result is false
        assertFalse(result);
    }

    @Test
    void testGetPaymentById() {
        String paymentId = "paymentId";
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "123", "12/23", "Completed");

        // Mock the findById method of the paymentRepository
        when(paymentRepository.findById(paymentId)).thenReturn(java.util.Optional.ofNullable(payment));

        // Call the getPaymentById method
        Payment result = paymentService.getPaymentById(paymentId);

        // Assert that the result is not null and matches the expected payment
        assertNotNull(result);
        assertEquals(payment, result);
    }
}