package com.example.PaymentControllerTest;



import com.example.demo.controller.PaymentController;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Item;
import com.example.demo.entities.Payment;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.services.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentControllerTest {

    @Mock
    private PaymentServiceImpl paymentServiceImpl;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldInitiatePaymentTransactionSuccessfully() {
        // Mock the paymentServiceImpl.initiatePaymentTransaction() method to return true when called
        when(paymentServiceImpl.initiatePaymentTransaction(any(Payment.class))).thenReturn(true);

        // Mock the cartRepository.findByUserId() method to return a cart when called
        Cart cart = new Cart();
        cart.setUserId("userId");
        Item item = new Item("itemId", "itemName", 50.0, 2);
        cart.addItem(item);
        when(cartRepository.findByUserId("userId")).thenReturn(cart);

        // Call the initiatePaymentTransaction endpoint in the controller
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "123", "12/23", "Pending");
        ResponseEntity<String> response = paymentController.initiatePaymentTransaction(payment, "userId");

        // Assert that the response is successful
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == null;
    }

    @Test
    public void shouldFailToInitiatePaymentTransaction_InvalidPaymentInfo() {
        // Mock the cartRepository.findByUserId() method to return a cart when called
        Cart cart = new Cart();
        cart.setUserId("userId");
        Item item = new Item("itemId", "itemName", 50.0, 2);
        cart.addItem(item);
        when(cartRepository.findByUserId("userId")).thenReturn(cart);

        // Call the initiatePaymentTransaction endpoint in the controller with invalid payment info
        Payment payment = new Payment("userId", 100.0, "", "123", "12/23", "Pending");
        ResponseEntity<String> response = paymentController.initiatePaymentTransaction(payment, "userId");

        // Assert that the response is a bad request with an error message
        assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
        assert response.getBody().equals("Fill all the requir");
    }

    @Test
    public void shouldGetUsersPurchases() {
        // Mock the paymentRepository.findPaymentsByUserId() method to return a list of payments when called
        Payment payment = new Payment("userId", 100.0, "1234567890123456", "123", "12/23", "Completed");
        when(paymentRepository.findPaymentsByUserId("userId")).thenReturn(Arrays.asList(payment));

        // Call the getUsersPurchases endpoint in the controller
        List<Payment> payments = paymentController.getUsersPurchases("userId");

        // Assert that the returned list contains the expected payment
        assertNotNull(payments);
        assertEquals(1, payments.size());
        assertEquals(payment, payments.get(0));
    }
}