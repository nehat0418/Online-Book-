package com.example.demo.controller;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Item;
import com.example.demo.entities.Payment;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.services.PaymentServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/transaction/{userId}")
    public ResponseEntity<String> initiatePaymentTransaction(@RequestBody Payment payment, @PathVariable String userId) {
    	// Check if payment information is filled or not
    	 if (payment.getCardNumber().equals("") || payment.getCVV().equals("") || payment.getExpirationDate().equals("") ) {
    		        return ResponseEntity.badRequest().body("Fill all the requir");
    	 }
    	 
    	// Fetch the user's cart from the repository.
    	Cart cart = cartRepository.findByUserId(userId);
    	if (cart == null) {
	        return ResponseEntity.badRequest().body("Unable to checkout");
    	}
    	//calculating the total amount of book purchased
    	int total = 0;
    	for (int i = 0; i < cart.getItems().size(); i++) {
    		Item item = cart.getItems().get(i);
    		total += item.getPrice() * item.getQuantity();
    	}
    	payment.setAmount(total);
    	payment.setUserId(userId);
    	payment.setItems(cart.getItems());
    	// Initiate the payment transaction.
        boolean transactionResult = paymentServiceImpl.initiatePaymentTransaction(payment);
        cart.clearCart();
        cartRepository.save(cart);
        if (transactionResult) {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } else {
            return ResponseEntity.badRequest().body("Failed to initiate payment transaction.");
        }
    }

    @GetMapping("/{userId}/purchases")
    public List<Payment> getUsersPurchases(@PathVariable String userId) {
    	return paymentRepository.findPaymentsByUserId(userId);
    }
    
    @PostMapping("/validate")
    public ResponseEntity<String> validatePaymentInformation(@RequestBody Payment payment) {
        // Validate payment information
    	
        boolean validationResult = paymentServiceImpl.validatePaymentInformation(payment);
        System.out.println(validationResult);
        
        if (validationResult) {
            return ResponseEntity.ok("Payment information is valid.");
        } else {
            
            System.out.println("Invalid payment information: " + payment.toString());      
            return ResponseEntity.badRequest().body("Invalid payment information.");
        }
   }

    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String paymentId) {
    	// Fetch the payment by its ID using the service.
    	Payment payment = paymentServiceImpl.getPaymentById(paymentId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
