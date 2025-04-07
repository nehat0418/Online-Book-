package com.example.demo.controller;


import com.example.demo.entities.*;
import com.example.demo.services.CartService;
import com.example.demo.services.CartServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.hc.core5.http.HttpStatus;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	  
	private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
	@PostMapping("/{userId}/add")
    public ResponseEntity<String> addItemToCart(@RequestBody Item item, @PathVariable String userId) {
		
	 	System.out.print(item.getItemId());
	 	
	 	//Validate if item id
        if (item.getItemId().equals("")) {
            return ResponseEntity.badRequest().body("Item ID cannot be null.");
        }
        
        // Call the service to add the item to the user's cart.
        boolean isItemAdded = cartService.addItemToCart(userId, item) != null;
        
        if (isItemAdded) {
            return ResponseEntity.ok("Item added to cart.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add item to cart.");
        }
        
    }
	 
	 
	@GetMapping("/{userId}/view")
	public ResponseEntity<?> viewCart(@PathVariable String userId) {
	    return ResponseEntity.ok(cartService.getAllItemsInCart(userId));
	}

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestBody Item item, @PathVariable String userId) {
        boolean isItemRemoved = cartService.removeItemFromCart(userId, item.getItemId());
        if (isItemRemoved) {
            return ResponseEntity.ok("Item removed from cart.");
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Item not found in cart.");
        }
    }
    
    @PostMapping("/{userId}/checkout")
    public String checkoutCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return "Cart checked out successfually.";
    }
}
