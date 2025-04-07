package com.example.demo.services;



import com.example.demo.entities.Item;

import java.util.List;

public interface CartService {
   
   Item addItemToCart(String cartId, Item item);

   boolean removeItemFromCart(String cartId, String itemId);

	void clearCart();

	List<Item> getAllItemsInCart(String cartId);

	void clearCart(String cartId);

	Object getAllItemsInCart();

}
