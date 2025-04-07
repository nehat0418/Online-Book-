package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.Item;
import com.example.demo.repository.CartRepository;
import com.mongodb.client.model.Filters;

import java.util.List;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;



@Service
public class CartServiceImpl implements CartService {
	
	private final CartRepository cartRepository;
	
	 public CartServiceImpl(CartRepository cartRepository) {
	        this.cartRepository = cartRepository;
	    }
	
	private Object existingCart() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
    public Item addItemToCart(String userId, Item item) {
		
		// Check if the user has item in a cart.
		Cart existingCart =(Cart) cartRepository.findByUserId(userId);
		if(existingCart == null) {
		// If the user doesn't have a cart, create a new one and set the user ID.
			existingCart = new Cart();
			existingCart.setUserId(userId);
		}
		existingCart.addItem(item);
		cartRepository.save(existingCart);
        return item;
    }

	@Override
    public void clearCart(String userId) {
		Cart existingCart =(Cart) cartRepository.findByUserId(userId);
		// Check if the cart exists before clearing it.
        if (existingCart().equals(existingCart)) {
            existingCart.clearCart();
            cartRepository.save(existingCart);
        }
        cartRepository.save(existingCart);
    }

	@Override
    public boolean removeItemFromCart(String userId, String itemId) {
		Cart existingCart =(Cart) cartRepository.findByUserId(userId);
		//check if existing cart is not empty
		if(existingCart != null) {
			existingCart.removeItem(itemId);
		}
        cartRepository.save(existingCart);
		return false;
    }

	@Override
	public List<Item> getAllItemsInCart(String userId) {
		// Retrieve the cart for the given user ID from the repository.
		Cart existingCart =(Cart) cartRepository.findByUserId(userId);
		return existingCart.getItems();
	}

	@Override
	public void clearCart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAllItemsInCart() {
		// TODO Auto-generated method stub
		return null;
	}
	
}