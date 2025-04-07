package com.example.demo.entities;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection="cart_items")
@Component
public class Cart {
	@Id
	private String id;
    private List<Item> items;
    private String userId;
    
    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
    	boolean found = false;
    	
    	//use to increment number of item purchased, in cart
    	for(int i = 0; i < this.items.size(); i++) {
    		if(this.items.get(i).getItemId().equals(item.getItemId())) {
    			Item cartItem = this.items.get(i);
    			cartItem.setQuantity(cartItem.getQuantity() + 1);
    			found = true;
    			this.items.set(i, cartItem);
    		}
    	}
    	
    	if(this.items.size() == 0 || !found) {
    		this.items.add(item);
    	}
    }

    
    public boolean removeItem(String itemId) {
    	
        //use to decrement number of item purchased, in cart
    	for(int i = 0; i < this.items.size(); i++) {
    		if(this.items.get(i).getItemId().equals(itemId)) {
    		Item item = this.items.get(i);
            if (item.getItemId().equals(itemId)) {
            	if(item.getQuantity() == 1) {
            		this.items.remove(i);
            	} else {
            		item.setQuantity(item.getQuantity() - 1);
        			this.items.set(i, item);
            	}
              return true;
            }
        }
    }
        return false;
    }
    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void clearCart() {
        items.clear();
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
